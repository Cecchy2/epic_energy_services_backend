package epic_team6.buildweek_epic_energy_services.payloads;

import epic_team6.buildweek_epic_energy_services.enums.TipologiaCliente;

import java.time.LocalDate;
import java.util.UUID;

public record ClientePayloadDTO(
        String ragioneSociale,
        String partitaIva,
        String email,
        LocalDate dataUltimoContatto,
        double fatturatoAnnuale,
        String pec,
        String telefono,
        String emailContatto,
        String nomeContatto,
        String cognomeContatto,
        String telefonoContatto,

        TipologiaCliente tipologia,
        UUID indirizzoSedeLegale,
        UUID indirizzoSedeOperativa) {
}
