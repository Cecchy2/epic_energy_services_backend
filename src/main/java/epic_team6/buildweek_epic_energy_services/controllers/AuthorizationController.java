package epic_team6.buildweek_epic_energy_services.controllers;

import epic_team6.buildweek_epic_energy_services.entities.Utente;
import epic_team6.buildweek_epic_energy_services.enums.RuoloUtente;
import epic_team6.buildweek_epic_energy_services.exceptions.BadRequestException;
import epic_team6.buildweek_epic_energy_services.payloads.UtenteLoginDTO;
import epic_team6.buildweek_epic_energy_services.payloads.UtenteLoginResponseDTO;
import epic_team6.buildweek_epic_energy_services.payloads.UtentiPayloadDTO;
import epic_team6.buildweek_epic_energy_services.payloads.UtentiResponseDTO;
import epic_team6.buildweek_epic_energy_services.services.AuthorizationsService;
import epic_team6.buildweek_epic_energy_services.services.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authorization")
public class AuthorizationController {
    @Autowired
    private AuthorizationsService authorizationsService;
    @Autowired
    private UtentiService utentiService;


    @PostMapping("/login")
    public UtenteLoginResponseDTO login(@RequestBody UtenteLoginDTO body){
        Utente found = this.utentiService.findByEmail(body.email());
        RuoloUtente role= found.getRuolo();
        UUID utenteId = found.getId();
        return new UtenteLoginResponseDTO(this.authorizationsService.checkCredenzialiEGeneraToken(body),role, utenteId);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UtentiResponseDTO save(@RequestBody @Validated UtentiPayloadDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));

            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {

            return new UtentiResponseDTO(this.utentiService.saveUtente(body).getId());
        }
    }



}
