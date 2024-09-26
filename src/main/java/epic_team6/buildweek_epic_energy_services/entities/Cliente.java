package epic_team6.buildweek_epic_energy_services.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import epic_team6.buildweek_epic_energy_services.enums.TipologiaCliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "clienti")
@JsonIgnoreProperties({"fatture"})
public class Cliente {

    @Id
    @GeneratedValue
    private UUID id;
    private String ragioneSociale;
    private String partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private double fatturatoAnnuale;
    private String pec;
    private String telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;
    private String logoAziendale;

    @Enumerated(EnumType.STRING)
    private TipologiaCliente tipologia;
    @OneToOne
    @JoinColumn(name = "indirizzo_sede_legale_id")
    private Indirizzo indirizzoSedeLegale_id;
    @OneToOne
    @JoinColumn(name = "indirizzo_sede_operativa_id")
    private Indirizzo indirizzoSedeOperativa_id;

    @OneToMany(mappedBy = "cliente")

    private List<Fattura> fatture;


    public Cliente(String ragioneSociale, String partitaIva, String email, LocalDate dataInserimento, LocalDate dataUltimoContatto, double fatturatoAnnuale, String pec, String telefono, String emailContatto, String nomeContatto, String cognomeContatto, String telefonoContatto, String logoAziendale, TipologiaCliente tipologia, Indirizzo indirizzoSedeLegale, Indirizzo indirizzoSedeOperativa) {
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIva;
        this.email = email;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.pec = pec;
        this.telefono = telefono;
        this.emailContatto = emailContatto;
        this.nomeContatto = nomeContatto;
        this.cognomeContatto = cognomeContatto;
        this.telefonoContatto = telefonoContatto;
        this.logoAziendale = logoAziendale;
        this.tipologia = tipologia;
        this.indirizzoSedeLegale_id = indirizzoSedeLegale;
        this.indirizzoSedeOperativa_id = indirizzoSedeOperativa;
    }
}
