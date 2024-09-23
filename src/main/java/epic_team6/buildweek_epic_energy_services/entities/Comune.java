package epic_team6.buildweek_epic_energy_services.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comune {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String codiceProvincia;
    private String codiceProgressivo;
    private String nome;
    @ManyToOne
    private Provincia provincia;

    public Comune(String codiceProvincia, String codiceProgressivo, String nome, Provincia provincia) {
        this.codiceProvincia = codiceProvincia;
        this.codiceProgressivo = codiceProgressivo;
        this.nome = nome;
        this.provincia = provincia;
    }
}
