package epic_team6.buildweek_epic_energy_services.controllers;

import epic_team6.buildweek_epic_energy_services.exceptions.BadRequestException;
import epic_team6.buildweek_epic_energy_services.payloads.FattureRespDTO;
import epic_team6.buildweek_epic_energy_services.payloads.NewFatturaDTO;
import epic_team6.buildweek_epic_energy_services.payloads.UpdateStatoFatturaDTO;
import epic_team6.buildweek_epic_energy_services.services.FattureService;
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
@RequestMapping("/fatture")
public class FattureController {
    @Autowired
    private FattureService fatturaService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<FattureRespDTO> findAll(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "15") int size,
                                        @RequestParam(defaultValue = "id") String sortBy) {
        return this.fatturaService.findAll(page, size, sortBy);
    }

    @GetMapping("/{fatturaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public FattureRespDTO findById(@PathVariable UUID fatturaId) {
        return this.fatturaService.findByIdResponse(fatturaId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public FattureRespDTO save(@RequestBody @Validated NewFatturaDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            String messages = validation.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));

            throw new BadRequestException("ci sono stati errori nel payload: " + messages);
        }

        return this.fatturaService.save(body);
    }

    @PutMapping("/{fatturaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public FattureRespDTO findByIdAndUpdate(@PathVariable UUID fatturaId, @RequestBody @Validated NewFatturaDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            String messages = validation.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));

            throw new BadRequestException("ci sono stati errori nel payload: " + messages);
        }

        return this.fatturaService.findByIdAndUpdate(fatturaId, body);
    }

    @PatchMapping("/{fatturaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public FattureRespDTO updateStatoFatturaById(@PathVariable UUID fatturaId, @RequestBody @Validated UpdateStatoFatturaDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            String messages = validation.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));

            throw new BadRequestException("ci sono stati errori nel payload: " + messages);
        }

        return this.fatturaService.updateStatoFatturaById(fatturaId, body);
    }

    @DeleteMapping("/{fatturaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID fatturaId) {
        this.fatturaService.delete(fatturaId);
    }

    @GetMapping("/cliente")
    public List<Fattura> filtraPerCliente(@RequestParam UUID clienteId) {
        return fatturaService.getFattureByClienteId(clienteId);
    }

    @GetMapping("/statoFatture")
    public List<Fattura> filtraPerStato(@RequestParam StatoFattura statoFatture) {
        return fatturaService.getFattureByStato(statoFatture);
    }

    @GetMapping("/anno")
    public List<Fattura> filtraFatturePerAnno(@RequestParam int anno) {
        return fatturaService.getFatturaByAnno(anno);
    }

    @GetMapping("/importo")
    public List<Fattura> filtrapFatturePerImporto(@RequestParam double minimoImporto, @RequestParam double massimoImporto) {
        return fatturaService.findByImporto(minimoImporto, massimoImporto);
    }

}
