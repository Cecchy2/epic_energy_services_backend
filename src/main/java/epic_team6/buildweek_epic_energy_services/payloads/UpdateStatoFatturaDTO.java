package epic_team6.buildweek_epic_energy_services.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record UpdateStatoFatturaDTO(@Pattern(regexp = "CREATA|INVIATA|PAGATA|SCADUTA|ANNULLATA|RIMBORSATA",
        message = "Lo stato della fattura deve essere uno tra CREATA, INVIATA, PAGATA, SCADUTA, ANNULLATA, RIMBORSATA")
                                    @NotEmpty(message = "Lo stato fattura è obbligatorio!")
                                    String statoFattura) {
}
