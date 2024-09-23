package epic_team6.buildweek_epic_energy_services.entities;

import epic_team6.buildweek_epic_energy_services.enums.StatoFattura;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Random;
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
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Fattura(LocalDate dataFattura, double importo, StatoFattura statoFattura, Cliente cliente) {
        this.dataFattura = dataFattura;
        this.importo = importo;
        this.numeroFattura = getNumeroFattura();
        this.statoFattura = statoFattura;
        this.cliente = cliente;
    }


    private String generaNumeroFattura() {

        int randomNumber = new Random().nextInt(9000) + 1000;  // Un numero casuale tra 1000 e 9999

        // Usa il timestamp o un contatore incrementale per garantire l'unicità
        long timePart = System.currentTimeMillis();

        // Combina la parte temporale con la parte casuale
        return "F" + timePart + "-" + randomNumber;
    }
}
