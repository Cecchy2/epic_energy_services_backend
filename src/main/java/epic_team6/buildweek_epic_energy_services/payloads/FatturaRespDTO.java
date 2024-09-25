package epic_team6.buildweek_epic_energy_services.payloads;

import epic_team6.buildweek_epic_energy_services.enums.StatoFattura;

import java.time.LocalDate;
import java.util.UUID;

public record FatturaRespDTO(UUID id,
                             LocalDate dataFattura,
                             double importo,
                             String numeroFattura,
                             StatoFattura statoFattura,
                             String clienteId) {
}
