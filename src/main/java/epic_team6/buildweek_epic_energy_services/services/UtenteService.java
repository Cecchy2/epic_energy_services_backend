package epic_team6.buildweek_epic_energy_services.services;

import epic_team6.buildweek_epic_energy_services.entities.Provincia;
import epic_team6.buildweek_epic_energy_services.entities.Utente;
import epic_team6.buildweek_epic_energy_services.exceptions.BadRequestException;
import epic_team6.buildweek_epic_energy_services.exceptions.NotFoundException;
import epic_team6.buildweek_epic_energy_services.payloads.UtentePayloadDTO;
import epic_team6.buildweek_epic_energy_services.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public Page<Utente> findAll(int page, int size, String sortBy) {
        if (page > 100) page = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.utenteRepository.findAll(pageable);
    }

    public Utente findUtenteById(UUID utenteId){
        Utente found = this.utenteRepository.findById(utenteId).orElseThrow(()->new NotFoundException(utenteId));
        return found;
    }

    public Utente saveUtente (UtentePayloadDTO body){
        if (utenteRepository.existsByEmail(body.email())) throw new BadRequestException("L' email " + body.email() + " è già in uso");
        String avatar = "https://ui-avatars.com/api/?name="+body.nome()+"+"+body.cognome();
        Utente newUtente = new Utente(body.username(), body.email(), body.password(), body.nome(), body.cognome(),avatar) ;
        return utenteRepository.save(newUtente);
    }
}
