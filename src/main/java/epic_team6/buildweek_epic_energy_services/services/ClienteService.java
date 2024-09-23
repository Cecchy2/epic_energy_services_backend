package epic_team6.buildweek_epic_energy_services.services;

import epic_team6.buildweek_epic_energy_services.entities.Cliente;
import epic_team6.buildweek_epic_energy_services.enums.TipologiaCliente;
import epic_team6.buildweek_epic_energy_services.exceptions.NotFoundException;
import epic_team6.buildweek_epic_energy_services.payloads.ClienteDTO;
import epic_team6.buildweek_epic_energy_services.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    // Metodo per salvare un cliente
    public Cliente salvaCliente(ClienteDTO body) {
        Cliente cliente = new Cliente(body.ragioneSociale(), body.partitaIva(), body.email(), body.dataInserimento(), body.dataUltimoContatto(), body.fatturatoAnnuale(), body.pec(), body.telefono(), body.emailContatto(), body.nomeContatto(), body.cognomeContatto(), body.telefonoContatto(), body.logoAziendale(), TipologiaCliente.valueOf(body.tipologia()), body.indirizzoSedeLegale(), body.indirizzoSedeOperativa());
        return clienteRepository.save(cliente);
    }


    // Metodo per ottenere tutti i clienti
    public List<Cliente> trovaTuttiClienti() {
        return clienteRepository.findAll();
    }

    // Metodo per ottenere un cliente per ID
    public Cliente trovaClienteById(UUID id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    // Metodo per eliminare un cliente per ID
    public void cancellaClienteById(UUID id) {
        Cliente cliente = trovaClienteById(id);
        clienteRepository.delete(cliente);
    }

    public Cliente aggiornaESalvaCliente(UUID id, Cliente cliente) {
        return clienteRepository.findById(id)
                .map(clienteEsistente -> {
                    clienteEsistente.setRagioneSociale(cliente.getRagioneSociale() != null ? cliente.getRagioneSociale() : clienteEsistente.getRagioneSociale());
                    clienteEsistente.setPartitaIva(cliente.getPartitaIva() != null ? cliente.getPartitaIva() : clienteEsistente.getPartitaIva());
                    clienteEsistente.setEmail(cliente.getEmail() != null ? cliente.getEmail() : clienteEsistente.getEmail());
                    clienteEsistente.setTelefono(cliente.getTelefono() != null ? cliente.getTelefono() : clienteEsistente.getTelefono());
                    clienteEsistente.setFatturatoAnnuale(cliente.getFatturatoAnnuale() != 0 ? cliente.getFatturatoAnnuale() : clienteEsistente.getFatturatoAnnuale());
                    clienteEsistente.setNomeContatto(cliente.getNomeContatto() != null ? cliente.getNomeContatto() : clienteEsistente.getNomeContatto());
                    clienteEsistente.setCognomeContatto(cliente.getCognomeContatto() != null ? cliente.getCognomeContatto() : clienteEsistente.getCognomeContatto());
                    clienteEsistente.setEmailContatto(cliente.getEmailContatto() != null ? cliente.getEmailContatto() : clienteEsistente.getEmailContatto());
                    clienteEsistente.setTelefonoContatto(cliente.getTelefonoContatto() != null ? cliente.getTelefonoContatto() : clienteEsistente.getTelefonoContatto());
                    clienteEsistente.setIndirizzoSedeLegale(cliente.getIndirizzoSedeLegale() != null ? cliente.getIndirizzoSedeLegale() : clienteEsistente.getIndirizzoSedeLegale());
                    clienteEsistente.setIndirizzoSedeOperativa(cliente.getIndirizzoSedeOperativa() != null ? cliente.getIndirizzoSedeOperativa() : clienteEsistente.getIndirizzoSedeOperativa());
                    clienteEsistente.setTipologia(cliente.getTipologia() != null ? cliente.getTipologia() : clienteEsistente.getTipologia());
                    return clienteRepository.save(clienteEsistente);
                })
                .orElseGet(() -> clienteRepository.save(cliente));
    }

}
