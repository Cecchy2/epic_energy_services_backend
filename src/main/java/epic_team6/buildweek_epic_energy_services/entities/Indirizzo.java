package epic_team6.buildweek_epic_energy_services.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "indirizzo")
public class Indirizzo {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String via;
    private int civico;
    private String localita;
    private int cap;
    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comune;


    public Indirizzo(String via, int civico, String localita, int cap, Comune comune) {
        this.via = via;
        this.civico = civico;
        this.localita = localita;
        this.cap = cap;
        this.comune = comune;

    }
}
