package epic_team6.buildweek_epic_energy_services.services;

import epic_team6.buildweek_epic_energy_services.entities.Provincia;
import epic_team6.buildweek_epic_energy_services.repositories.ProvincieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvincieService {
    @Autowired
    private ProvincieRepository provincieRepository;


    public List<Provincia> saveAll (List<Provincia> province){
        return provincieRepository.saveAll(province);
    }
}
