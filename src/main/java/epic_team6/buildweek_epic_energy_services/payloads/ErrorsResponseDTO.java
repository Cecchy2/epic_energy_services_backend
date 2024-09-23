package epic_team6.buildweek_epic_energy_services.payloads;

import java.time.LocalDateTime;

public record ErrorsResponseDTO(String message, LocalDateTime timeStamp) {
}
