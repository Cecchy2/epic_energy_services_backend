package epic_team6.buildweek_epic_energy_services.payloads;

import epic_team6.buildweek_epic_energy_services.enums.RuoloUtente;

public record UtenteLoginResponseDTO(String accessToken, RuoloUtente role) {
}
