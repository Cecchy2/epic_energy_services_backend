package epic_team6.buildweek_epic_energy_services.entities;

import epic_team6.buildweek_epic_energy_services.enums.TipologiaCliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "clienti")
public class Cliente {

    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private String ragioneSociale;
    @Column
    private String partitaIva;
    @Column
    private String email;
    @Column
    private LocalDate dataInserimento;
    @Column
    private LocalDate dataUltimoContatto;
    @Column
    private double fatturatoAnnuale;
    @Column
    private String pec;
    @Column
    private String telefono;
    @Column
    private String emailContatto;
    @Column
    private String nomeContatto;
    @Column
    private String cognomeContatto;
    @Column
    private String telefonoContatto;
    @Column
    private String logoAziendale;
    @Enumerated(EnumType.STRING)
    @Column
    private TipologiaCliente tipologia;
    @Column
    private String indirizzoSedeLegale;
    @Column
    private String indirizzoSedeOperativa;
    @OneToMany(mappedBy = "cliente")
    private List<Fattura> fatture;

    public Cliente(String ragioneSociale, String partitaIva, String email, LocalDate dataInserimento, LocalDate dataUltimoContatto, double fatturatoAnnuale, String pec, String telefono, String emailContatto, String nomeContatto, String cognomeContatto, String telefonoContatto, String logoAziendale, TipologiaCliente tipologia, String indirizzoSedeLegale, String indirizzoSedeOperativa) {
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
        this.indirizzoSedeLegale = indirizzoSedeLegale;
        this.indirizzoSedeOperativa = indirizzoSedeOperativa;

    }

}
