package epic_team6.buildweek_epic_energy_services.entities;

import epic_team6.buildweek_epic_energy_services.enums.TipologiaCliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
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
    private Date dataInserimento;

    @Column
    private Date dataUltimoContatto;

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

}
