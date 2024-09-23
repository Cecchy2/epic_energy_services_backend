package epic_team6.buildweek_epic_energy_services.entities;

import epic_team6.buildweek_epic_energy_services.enums.StatoFattura;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "fatture")
public class Fattura {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(name = "data_fattura")
    private LocalDate dataFattura;
    private double importo;
    @Column(name = "numero_fattura")
    private String numeroFattura;
    @Column(name = "stato_fattura")
    @Enumerated(EnumType.STRING)
    private StatoFattura statoFattura;

    public Fattura(LocalDate dataFattura, double importo, String numeroFattura, StatoFattura statoFattura) {
        this.dataFattura = dataFattura;
        this.importo = importo;
        this.numeroFattura = numeroFattura;
        this.statoFattura = statoFattura;
    }
}
