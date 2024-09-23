package epic_team6.buildweek_epic_energy_services.controllers;

import epic_team6.buildweek_epic_energy_services.entities.Utente;
import epic_team6.buildweek_epic_energy_services.exceptions.BadRequestException;
import epic_team6.buildweek_epic_energy_services.exceptions.NotFoundException;
import epic_team6.buildweek_epic_energy_services.payloads.UtentiPayloadDTO;
import epic_team6.buildweek_epic_energy_services.payloads.UtentiResponseDTO;
import epic_team6.buildweek_epic_energy_services.services.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/utenti")
public class UtentiController {
    @Autowired
    private UtentiService utenteService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UtentiResponseDTO save (@RequestBody @Validated UtentiPayloadDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()){
            String message = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono errori nel payload " + message);
        }else{
            return new UtentiResponseDTO(this.utenteService.saveUtente(body).getId());
        }
    }

    @GetMapping
    public Page<Utente> findAll(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "15") int size,
                                        @RequestParam(defaultValue = "id") String sortBy) {
        return this.utenteService.findAll(page, size, sortBy);
    }

    @GetMapping("/{utentiId}")
    public Utente findById (@PathVariable UUID utenteId){
        Utente found = this.utenteService.findUtenteById(utenteId);
        if (found == null )throw new NotFoundException(utenteId);
        return found;
    }

    @PutMapping("/{utenteId}")
    public Utente findByIdAndUpdate(@PathVariable UUID utenteId,@RequestBody @Validated UtentiPayloadDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()){
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono errore con il payload " + message);
        }else {
            return this.utenteService.findByIdAndUpdate(utenteId,body);
        }
    }

    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID utenteId){
        this.utenteService.findByIdAndDeleteUtente(utenteId);
    }

}
