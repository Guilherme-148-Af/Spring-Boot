package example.cashcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CashCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashCardApplication.class, args);
	}

}


/* package example.cashcard;

import org.springframework.data.annotation.Id;
import java.time.Instant;

record Message(
    @Id Long id,
    String fromUser,
    String toUser,
    String content,
    Instant createdAt,
    boolean read
) {} */