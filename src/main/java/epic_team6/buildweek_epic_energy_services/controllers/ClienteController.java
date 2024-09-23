package epic_team6.buildweek_epic_energy_services.controllers;

import epic_team6.buildweek_epic_energy_services.entities.Cliente;
import epic_team6.buildweek_epic_energy_services.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.salvaCliente(cliente);
    }

    // Elimina un cliente per ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable UUID id) {
        clienteService.cancellaClienteById(id);
    }
}
