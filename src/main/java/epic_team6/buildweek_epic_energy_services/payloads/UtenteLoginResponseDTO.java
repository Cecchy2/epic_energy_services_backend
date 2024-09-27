package epic_team6.buildweek_epic_energy_services.payloads;

import epic_team6.buildweek_epic_energy_services.enums.RuoloUtente;

import java.util.UUID;

public record UtenteLoginResponseDTO(String accessToken, RuoloUtente role, UUID utenteId) {
}
