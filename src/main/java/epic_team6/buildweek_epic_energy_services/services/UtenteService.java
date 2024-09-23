package epic_team6.buildweek_epic_energy_services.services;

import epic_team6.buildweek_epic_energy_services.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
}
