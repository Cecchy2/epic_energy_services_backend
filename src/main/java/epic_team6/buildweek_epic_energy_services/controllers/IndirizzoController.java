package epic_team6.buildweek_epic_energy_services.controllers;

import epic_team6.buildweek_epic_energy_services.entities.Indirizzo;
import epic_team6.buildweek_epic_energy_services.exceptions.BadRequestException;
import epic_team6.buildweek_epic_energy_services.payloads.IndirizzoDTO;
import epic_team6.buildweek_epic_energy_services.payloads.NewIndirizzoRespDTO;
import epic_team6.buildweek_epic_energy_services.repositories.IndirizziResponsDTO;
import epic_team6.buildweek_epic_energy_services.services.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/indirizzi")
public class IndirizzoController {
    @Autowired
    private IndirizzoService indirizzoService;

    @GetMapping
    public Page<Indirizzo> findAll(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "1") int size,
                                   @RequestParam(defaultValue = "uuid") String sortBy) {
        return this.indirizzoService.findAll(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IndirizziResponsDTO save(@RequestBody @Validated IndirizzoDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()){
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(" ."));
            throw new BadRequestException("Errore nel payload " + message);
        }else{
            return this.indirizzoService.creaIndirizzo(body);
        }
    }
    }

    /*@GetMapping("/{indirizzoId}")
    public Indirizzo findById(@PathVariable UUID indirizzoId) {
        return this.findById(indirizzoId);
    }

    @PutMapping("/{indirizzoId}")
    public Indirizzo findByIdAndUpdate(@PathVariable UUID indirizzoId, @RequestBody Indirizzo body) {
        return this.indirizzoService.findByIdAndUpdate(indirizzoId, body);
    }

    @DeleteMapping("/{indirizzoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID indirizzoId) {
        this.indirizzoService.findByIdAndDelete(indirizzoId);
    }*/

