package epic_team6.buildweek_epic_energy_services.payloads;

import epic_team6.buildweek_epic_energy_services.entities.Comune;
import epic_team6.buildweek_epic_energy_services.entities.Provincia;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record IndirizzoDTO(
        @NotEmpty(message = "via obbligatoria")
        @Size(min = 10, max = 40, message = "inserisci da 10 a massimo 40 caratteri")
        String via,
        @NotEmpty(message = "civico obbligatorio")
        @Size(min = 1, max = 3, message = "inserisci da 1 a massimo 3 caratteri")
        int civico,
        @NotEmpty(message = "località obbligatoria")
        @Size(min = 10, max = 40, message = "inserisci da 10 a massimo 40 caratteri")
        String localita,
        @NotEmpty(message = "cap obbligatorio")
        @Size(min = 5, max = 5, message = "inserisci 5 caratteri")
        int cap,
        @NotEmpty(message = "id del comune obbligatorio")
        @Size(min = 36, max = 36, message = "inserisci 36 caratteri")
        Comune comune,
        @NotEmpty(message = "id della provincia obbligatoria")
        @Size(min = 36, max = 36, message = "inserisci 36 caratteri")
        Provincia provincia
) {
}
