package epic_team6.buildweek_epic_energy_services.services;

import epic_team6.buildweek_epic_energy_services.entities.Utente;
import epic_team6.buildweek_epic_energy_services.exceptions.UnauthorizedException;
import epic_team6.buildweek_epic_energy_services.payloads.UtenteLoginDTO;
import epic_team6.buildweek_epic_energy_services.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationsService {
    @Autowired
    private UtentiService utentiService;
    @Autowired
    private JWTTools jwtTools;

    public String checkCredenzialiEGeneraToken(UtenteLoginDTO body){
        Utente found = this.utentiService.findByEmail(body.email());
        if (found.getPassword().equals(body.password())){
            return this.jwtTools.createToken(found);
        } else{
            throw new UnauthorizedException("Errore nelle credenziali che hai fornito");
        }
    }
}
