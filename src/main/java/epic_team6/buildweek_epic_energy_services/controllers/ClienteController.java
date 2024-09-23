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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ClientiService clienteService;


    @GetMapping
    public Page<Cliente> getAllClienti(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id")String sortby) {
        return clienteService.trovaTuttiClienti(page, size, sortby);
    }

    @GetMapping("/{dipendenteId}")
    public Cliente findById(@PathVariable UUID dipendenteId){
        Cliente found = this.clienteService.trovaClienteById(dipendenteId);
        if (found == null )throw new NotFoundException(dipendenteId);
        return found;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientiResponseDTO createCliente(@RequestBody @Validated ClientiPayloadDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()){
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));

            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        }else{
            return new ClientiResponseDTO(this.clienteService.salvaCliente(body).getId());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable UUID id) {
        this.clienteService.cancellaClienteById(id);
    }


}
