package epic_team6.buildweek_epic_energy_services.payloads;

import jakarta.validation.constraints.NotEmpty;

public record EmailPayloadDTO(@NotEmpty(message = "il titolo dell'email è obbligatorio!")
                              String emailSubject,
                              @NotEmpty(message = "Devi inserire il contenuto dell'email!")
                              String emailContent) {
}
