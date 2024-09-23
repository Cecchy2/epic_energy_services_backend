package epic_team6.buildweek_epic_energy_services.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Provincia {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private String sigla;
    private String nome;
    private String regione;
    @OneToMany(mappedBy = "provincia")
    private List<Comune> comuni;

    public Provincia(String sigla, String regione, String nome) {
        this.sigla = sigla;
        this.regione = regione;
        this.nome = nome;
    }
}
