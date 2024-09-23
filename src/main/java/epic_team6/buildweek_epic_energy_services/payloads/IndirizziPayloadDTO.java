package epic_team6.buildweek_epic_energy_services.payloads;


import jakarta.validation.constraints.*;

public record IndirizziPayloadDTO(
        @NotEmpty(message = "via obbligatoria")
        @Size(min = 10, max = 40, message = "inserisci da 10 a massimo 40 caratteri")
        String via,

        @NotNull(message = "civico obbligatorio")
        @Min(value = 1, message = "Il numero civico deve essere almeno 1")
        @Max(value = 999, message = "Il numero civico può essere massimo 999")
        int civico,

        @NotEmpty(message = "località obbligatoria")
        @Size(min = 10, max = 40, message = "inserisci da 10 a massimo 40 caratteri")
        String localita,

        @NotNull(message = "cap obbligatorio")
        @Min(value = 10000, message = "Il CAP deve essere almeno 5 cifre")
        @Max(value = 99999, message = "Il CAP deve essere esattamente 5 cifre")
        int cap,

        @NotEmpty(message = "id del comune obbligatorio")
        String comune_id,

        @NotEmpty(message = "id della provincia obbligatoria")
        String provincia_id
) {
}
