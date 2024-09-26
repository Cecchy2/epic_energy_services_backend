package epic_team6.buildweek_epic_energy_services.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ClientiPayloadDTO(

        @NotEmpty(message = "La ragione sociale è obbligatoria")

        String ragioneSociale,

        @NotEmpty(message = "La partita IVA è obbligatoria")
        @Size(min = 11, max = 11, message = "La partita IVA deve essere composta da 11 caratteri")
        String partitaIva,

        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "L'email inserita non è valida")
        String email,

        @NotNull(message = "La data dell'ultimo contatto non può essere null")
        LocalDate dataUltimoContatto,

        @NotNull(message = "Il fatturato annuale è obbligatorio")
        double fatturatoAnnuale,

        @NotEmpty(message = "La pec è obbligatoria")
        String pec,

        @NotEmpty(message = "Il telefono è obbligatorio")
        @Size(min = 10, max = 15, message = "Il telefono deve essere compreso tra 10 e 15 cifre")
        String telefono,

        @Email(message = "L'email del contatto non è valida")
        @NotEmpty(message = "L'email del contatto è obbligatoria")
        String emailContatto,

        @NotEmpty(message = "Il nome del contatto è obbligatorio")
        @Size(min = 2, max = 40, message = "Il nome del contatto deve essere compreso tra 2 e 40 caratteri")
        String nomeContatto,

        @NotEmpty(message = "Il cognome del contatto è obbligatorio")
        @Size(min = 2, max = 40, message = "Il cognome del contatto deve essere compreso tra 2 e 40 caratteri")
        String cognomeContatto,

        @NotEmpty(message = "Il telefono del contatto è obbligatorio")
        @Size(min = 10, max = 15, message = "Il telefono del contatto deve essere compreso tra 10 e 15 cifre")
        String telefonoContatto,

        @NotEmpty(message = "La tipologia è obbligatoria")
        @Pattern(regexp = "PA|SAS|SPA|SRL",
                message = "Lo stato della tipologia deve essere uno tra PA,SAS,SPA,SRL")
        String tipologia,

        @NotEmpty(message = "L'indirizzo della sede legale è obbligatorio")
        String indirizzoSedeLegale,


        @NotEmpty(message = "L'indirizzo della sede operativa è obbligatorio")
        String indirizzoSedeOperativa) {
}

