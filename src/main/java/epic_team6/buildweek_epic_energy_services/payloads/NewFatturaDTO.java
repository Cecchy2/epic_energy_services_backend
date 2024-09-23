package epic_team6.buildweek_epic_energy_services.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record NewFatturaDTO(@NotNull(message = "La data è obbligatoria!")
                            LocalDate dataFattura,
                            @NotNull(message = "L'importo della fattura è obbligatorio!")
                            @Positive(message = "il prezzo deve essere maggiore di zero!")
                            double importo,
                            @NotEmpty(message = "L'Id del cliente è obbligatorio!")
                            String clienteId) {
}
