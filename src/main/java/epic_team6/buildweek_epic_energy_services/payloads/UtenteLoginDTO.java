package epic_team6.buildweek_epic_energy_services.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UtenteLoginDTO(
        @NotEmpty(message = "La email è obbligatorio")
        String email,
        @NotEmpty(message = "inserisci una password")
        @Size(min = 3, max = 40, message = "La password deve essere compresa tra 3 e 40 caratteri")
        String password
) {
}
