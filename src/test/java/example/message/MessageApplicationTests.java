package example.message;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    record CreateMessageRequest(String name, String mensagem) {}

    @Test
    @DirtiesContext
    void shouldCreateANewMessage() {
        CreateMessageRequest newMessage = new CreateMessageRequest("João", "Olá mundo!");

        ResponseEntity<String> createResponse = restTemplate
                .postForEntity("/messages", newMessage, String.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewMessage = createResponse.getHeaders().getLocation();
        assertThat(locationOfNewMessage).isNotNull();

        DocumentContext documentContext = JsonPath.parse(createResponse.getBody());
        Number id = documentContext.read("$.id");
        String name = documentContext.read("$.name");
        String mensagem = documentContext.read("$.mensagem");

        assertThat(id).isNotNull();
        assertThat(name).isEqualTo("João");
        assertThat(mensagem).isEqualTo("Olá mundo!");
    }

    @Test
    @DirtiesContext
    void shouldFindMessageById() {
        CreateMessageRequest newMessage = new CreateMessageRequest("Maria", "Teste busca por ID");

        ResponseEntity<String> createResponse = restTemplate
                .postForEntity("/messages", newMessage, String.class);

        URI locationOfNewMessage = createResponse.getHeaders().getLocation();

        ResponseEntity<String> getResponse = restTemplate
                .getForEntity(locationOfNewMessage, String.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        String name = documentContext.read("$.name");
        String mensagem = documentContext.read("$.mensagem");

        assertThat(id).isNotNull();
        assertThat(name).isEqualTo("Maria");
        assertThat(mensagem).isEqualTo("Teste busca por ID");
    }

    @Test
    void shouldReturnNotFoundForNonExistentMessage() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/messages/99999", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
