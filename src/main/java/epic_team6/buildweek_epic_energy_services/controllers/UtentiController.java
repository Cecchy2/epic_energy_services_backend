package epic_team6.buildweek_epic_energy_services.controllers;

import epic_team6.buildweek_epic_energy_services.entities.Utente;
import epic_team6.buildweek_epic_energy_services.exceptions.BadRequestException;
import epic_team6.buildweek_epic_energy_services.payloads.FatturaRespDTO;
import epic_team6.buildweek_epic_energy_services.payloads.UtentePayloadDTO;
import epic_team6.buildweek_epic_energy_services.payloads.UtenteResponseDTO;
import epic_team6.buildweek_epic_energy_services.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/utenti")
public class UtentiController {
    @Autowired
    private UtenteService utenteService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UtenteResponseDTO save (@RequestBody @Validated UtentePayloadDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()){
            String message = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono errori nel payload " + message);
        }else{
            return new UtenteResponseDTO(this.utenteService.saveUtente(body).getId());
        }
    }

    @GetMapping
    public Page<Utente> findAll(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "15") int size,
                                        @RequestParam(defaultValue = "id") String sortBy) {
        return this.utenteService.findAll(page, size, sortBy);
    }
}
