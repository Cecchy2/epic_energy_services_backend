package epic_team6.buildweek_epic_energy_services;

import com.fasterxml.jackson.databind.ObjectMapper;
import epic_team6.buildweek_epic_energy_services.payloads.UtentiPayloadDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc // Per configurare MockMvc automaticamente
class BuildweekEpicEnergyServicesApplicationTests {

    @Autowired
    private MockMvc mockMvc;  // Strumento per simulare le richieste HTTP

    @Autowired
    private ObjectMapper objectMapper;  // Per convertire oggetti in JSON e viceversa

    @Test
    @WithMockUser
    public void testRegisterReturnCreatedWhenValidDataIsProvided() throws Exception {
        // 1. Preparo i dati di test: un oggetto che rappresenta l'utente da registrare
        UtentiPayloadDTO registrationDTO = new UtentiPayloadDTO("username", "email@example.com", "1234", "nome", "cognome");
        // 2. Converto l'oggetto in formato JSON
        String registrationJson = objectMapper.writeValueAsString(registrationDTO);
        // 3. Simulo la richiesta POST al nostro endpoint di registrazione
        mockMvc.perform(post("/authorization/register")
                        .contentType(MediaType.APPLICATION_JSON) //tipo di contenuto che sto inviando
                        .content(registrationJson)) //il contenuto JSON della richiesta
                .andExpect(status().isCreated()) //verifica che lo stato sia 201
                .andExpect(jsonPath("$.id").isString()); //verifica che la risposta contenga un ID

    }

}
