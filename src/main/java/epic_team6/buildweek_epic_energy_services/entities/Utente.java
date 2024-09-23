package epic_team6.buildweek_epic_energy_services.entities;

import epic_team6.buildweek_epic_energy_services.enums.RuoloUtente;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String username;
    private String email;
    private String password;
    private String nome;
    private String congome;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private RuoloUtente ruolo;

    public Utente(String username, String email, String password, String nome, String congome, String avatar) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.congome = congome;
        this.avatar = avatar;
        this.ruolo = RuoloUtente.USER;
    }
}
