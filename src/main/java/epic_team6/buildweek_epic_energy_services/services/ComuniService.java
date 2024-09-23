package epic_team6.buildweek_epic_energy_services.services;

import epic_team6.buildweek_epic_energy_services.entities.Comune;
import epic_team6.buildweek_epic_energy_services.repositories.ComuniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComuniService {
    @Autowired
    private ComuniRepository comuniRepository;


    public List<Comune> saveAll(List<Comune> comuni){
        return comuniRepository.saveAll(comuni);
    }
}
