package epic_team6.buildweek_epic_energy_services.controllers;

import epic_team6.buildweek_epic_energy_services.entities.Cliente;
import epic_team6.buildweek_epic_energy_services.exceptions.BadRequestException;
import epic_team6.buildweek_epic_energy_services.payloads.ClientePayloadDTO;
import epic_team6.buildweek_epic_energy_services.payloads.ClienteResponseDTO;
import epic_team6.buildweek_epic_energy_services.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Recupera tutti i clienti
    @GetMapping
    public List<Cliente> getAllClienti() {
        return clienteService.trovaTuttiClienti();
    }

    // Recupera un cliente per ID
    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable UUID id) {
        return clienteService.trovaClienteById(id);
    }

    // Crea un nuovo cliente
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO createCliente(@RequestBody @Validated ClientePayloadDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()){
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));

            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        }else{
            return new ClienteResponseDTO(this.clienteService.salvaCliente(body).getId());
        }

    }

    // Elimina un cliente per ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable UUID id) {
        clienteService.cancellaClienteById(id);
    }
}
