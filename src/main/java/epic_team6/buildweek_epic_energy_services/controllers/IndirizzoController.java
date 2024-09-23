package epic_team6.buildweek_epic_energy_services.controllers;

import epic_team6.buildweek_epic_energy_services.entities.Indirizzo;
import epic_team6.buildweek_epic_energy_services.payloads.IndirizzoDTO;
import epic_team6.buildweek_epic_energy_services.payloads.NewIndirizzoRespDTO;
import epic_team6.buildweek_epic_energy_services.services.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public NewIndirizzoRespDTO save(@RequestBody IndirizzoDTO body) {
        return new NewIndirizzoRespDTO(this.indirizzoService.saveIndirizzo(body).getId());
    }

    @GetMapping("/{indirizzoId}")
    public Indirizzo findById(@PathVariable UUID indirizzoId) {
        return this.indirizzoService.findById(indirizzoId);
    }

    @PutMapping("/{indirizzoId}")
    public Indirizzo findByIdAndUpdate(@PathVariable UUID indirizzoId, @RequestBody Indirizzo body) {
        return this.indirizzoService.findByIdAndUpdate(indirizzoId, body);
    }

    @DeleteMapping("/{indirizzoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID indirizzoId) {
        this.indirizzoService.findByIdAndDelete(indirizzoId);
    }
}
