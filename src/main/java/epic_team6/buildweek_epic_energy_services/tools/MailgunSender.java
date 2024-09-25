package epic_team6.buildweek_epic_energy_services.tools;

import epic_team6.buildweek_epic_energy_services.entities.Utente;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailgunSender {
    private String apiKey;
    private String domainName;

    public MailgunSender(@Value("${mailgun.key}") String apiKey, @Value("${mailgun.domain}") String domainName) {
        this.apiKey = apiKey;
        this.domainName = domainName;
    }

    public void sendRegistrationEmail(Utente recipient) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domainName + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", "kennyboateng.99@gmail.com")
                .queryString("to", recipient.getEmail())
                .queryString("subject", "Registrazione completata")
                .queryString("text", "Buongiorno " + recipient.getNome() + " " + recipient.getCognome() + " grazie per esserti registrato")
                .asJson();
        System.out.println((response.getBody()));
    }
//    public void sendMailByAdmin( Utente recipient){
//        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domainName + "/messages")
//                .basicAuth("api", this.apiKey)
//                .queryString("from", "kennyboateng.99@gmail.com")
//                .queryString("to", recipient.getEmail())
//                .queryString("subject", "blablabla")
//                .queryString("text", "Buongiorno " + recipient.getNome() + " " + recipient.getCognome() + " grazie per esserti registrato")
//    }
}
