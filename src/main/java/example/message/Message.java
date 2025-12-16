package example.message;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 500)
    private String mensagem;

    protected Message() {
        // construtor vazio obrigatório para JPA
    }

    public Message(String name, String mensagem) {
        this.name = name;
        this.mensagem = mensagem;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMensagem() {
        return mensagem;
    }
}
