package epic_team6.buildweek_epic_energy_services.controllers;

import epic_team6.buildweek_epic_energy_services.entities.Cliente;
import epic_team6.buildweek_epic_energy_services.exceptions.BadRequestException;
import epic_team6.buildweek_epic_energy_services.exceptions.NotFoundException;
import epic_team6.buildweek_epic_energy_services.payloads.ClientiPayloadDTO;
import epic_team6.buildweek_epic_energy_services.payloads.ClientiResponseDTO;
import epic_team6.buildweek_epic_energy_services.services.ClientiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ClientiService clienteService;


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Cliente> getAllClienti(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortby) {
        return clienteService.trovaTuttiClienti(page, size, sortby);
    }

    @GetMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente findById(@PathVariable UUID clienteId) {
        Cliente found = this.clienteService.trovaClienteById(clienteId);
        if (found == null) throw new NotFoundException(clienteId);
        return found;
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientiResponseDTO createCliente(@RequestBody @Validated ClientiPayloadDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));

            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {
            return new ClientiResponseDTO(this.clienteService.salvaCliente(body).getId());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable UUID id) {
        this.clienteService.cancellaClienteById(id);
    }

    @PatchMapping("/{clienteId}/pic")
    public Cliente uploadAvatarPic(@PathVariable UUID clienteId, @RequestParam("pic") MultipartFile pic) throws IOException {
        return this.clienteService.uploadLogoAziendale(clienteId, pic);
    }



   /* @GetMapping("/filter")
    public List<Cliente> clientiFilter(
            @RequestParam(required = false) Double minFatturato,
            @RequestParam(required = false) Double maxFatturato,
            @RequestParam(required = false) LocalDate inizioDataInserimento,
            @RequestParam(required = false) LocalDate fineDataInserimento,
            @RequestParam(required = false) LocalDate inizioDataContatto,
            @RequestParam(required = false) LocalDate fineDataContatto,
            @RequestParam(required = false) String parteNome) {

        return this.clienteService.findByFilters(minFatturato, maxFatturato, inizioDataInserimento, fineDataInserimento,
                inizioDataContatto, fineDataContatto, parteNome);
    }*/

    //*************** FILTRI *****************
    @GetMapping("/filterNome")
    public List<Cliente> filterByNome(@RequestParam String nome) {
        return this.clienteService.findByParteDelNome(nome);
    }

    @GetMapping("/filterFatturato")
    public List<Cliente> filterByFatturato(@RequestParam double minFatturato, @RequestParam double maxFatturato) {
        return this.clienteService.findByFatturatoAnnuale(minFatturato, maxFatturato);
    }

    @GetMapping("/filterDataInserimento")
    public List<Cliente> filterByDataInserimento(@RequestParam LocalDate inizioData, @RequestParam LocalDate fineData) {
        return this.clienteService.findByDataInserimento(inizioData, fineData);
    }

    @GetMapping("/filterDataUltimoContatto")
    public List<Cliente> filterByDataUltimoContatto(@RequestParam LocalDate inizioData, @RequestParam LocalDate fineData) {
        return this.clienteService.findByDataUltimoContatto(inizioData, fineData);
    }
}

