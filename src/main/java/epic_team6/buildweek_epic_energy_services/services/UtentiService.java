package epic_team6.buildweek_epic_energy_services.services;

import epic_team6.buildweek_epic_energy_services.entities.Utente;
import epic_team6.buildweek_epic_energy_services.exceptions.BadRequestException;
import epic_team6.buildweek_epic_energy_services.exceptions.NotFoundException;
import epic_team6.buildweek_epic_energy_services.payloads.UtentiPayloadDTO;
import epic_team6.buildweek_epic_energy_services.repositories.UtentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtentiService {
    @Autowired
    private UtentiRepository utenteRepository;

    public Page<Utente> findAll(int page, int size, String sortBy) {
        if (page > 100) page = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.utenteRepository.findAll(pageable);
    }

    public Utente findUtenteById(UUID utenteId){
        Utente found = this.utenteRepository.findById(utenteId).orElseThrow(()->new NotFoundException(utenteId));
        return found;
    }

    public Utente findByIdAndUpdate(UUID utenteId, UtentiPayloadDTO body){
        String avatar = "https://ui-avatars.com/api/?name="+body.nome()+"+"+body.cognome();
        Utente found = this.utenteRepository.findById(utenteId).orElseThrow(()->new NotFoundException(utenteId));
        if (found == null)throw new NotFoundException(utenteId);
        found.setUsername(body.username());
        found.setEmail(body.email());
        found.setPassword(body.password());
        found.setNome(body.nome());
        found.setCognome(body.cognome());

        return utenteRepository.save(found);
    }

    public Utente saveUtente (UtentiPayloadDTO body){
        if (utenteRepository.existsByEmail(body.email())) throw new BadRequestException("L' email " + body.email() + " è già in uso");
        String avatar = "https://ui-avatars.com/api/?name="+body.nome()+"+"+body.cognome();
        Utente newUtente = new Utente(body.username(), body.email(), body.password(), body.nome(), body.cognome(),avatar) ;
        return utenteRepository.save(newUtente);
    }
}
