package epic_team6.buildweek_epic_energy_services.controllers;

import epic_team6.buildweek_epic_energy_services.entities.Cliente;
import epic_team6.buildweek_epic_energy_services.payloads.ClienteDTO;
import epic_team6.buildweek_epic_energy_services.services.ClienteService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteRespDTO createCliente(@RequestBody @Validated ClienteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload: " + messages);
        }
        return new ClienteRespDTO(clienteService.salvaCliente(body).getId());
    }

    // Aggionamento cliente
    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable UUID id, @RequestBody @Valid ClienteDTO body) {
        return clienteService.aggiornaESalvaCliente(id, body);
    }

    // Elimina un cliente per ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable UUID id) {
        clienteService.cancellaClienteById(id);
    }
}

