package epic_team6.buildweek_epic_energy_services.services;

import epic_team6.buildweek_epic_energy_services.entities.Indirizzo;
import epic_team6.buildweek_epic_energy_services.payloads.IndirizzoDTO;
import epic_team6.buildweek_epic_energy_services.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IndirizzoService {
    @Autowired
    private IndirizzoRepository indirizzoRepository;

    public Indirizzo findById(UUID indirizzoId) {
        return this.indirizzoRepository.findById(indirizzoId).orElseThrow();
    }

    public Indirizzo saveIndirizzo(IndirizzoDTO body) {
        Indirizzo indirizzo = new Indirizzo(body.via(), body.civico(), body.localita(), body.cap(), body.comune(), body.provincia());
        Indirizzo savedIndirizzo = this.indirizzoRepository.save(indirizzo);
        return savedIndirizzo;
    }
}
