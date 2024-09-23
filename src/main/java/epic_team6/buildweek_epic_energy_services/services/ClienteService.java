package epic_team6.buildweek_epic_energy_services.services;

import epic_team6.buildweek_epic_energy_services.entities.Cliente;
import epic_team6.buildweek_epic_energy_services.exceptions.BadRequestException;
import epic_team6.buildweek_epic_energy_services.exceptions.NotFoundException;
import epic_team6.buildweek_epic_energy_services.payloads.ClientePayloadDTO;
import epic_team6.buildweek_epic_energy_services.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente salvaCliente(ClientePayloadDTO body) {
        if (clienteRepository.existsByEmail(body.email()) || clienteRepository.existsByPartitaIva(body.partitaIva())){
            throw new BadRequestException("Il cliente è già registrato");
        }

        Cliente cliente = new Cliente(body.ragioneSociale(),
                body.partitaIva(), body.email(), LocalDate.now(), body.dataUltimoContatto(),body.fatturatoAnnuale(),
                body.pec(),body.telefono(),body.emailContatto(), body.nomeContatto(), body.cognomeContatto(),
                body.telefonoContatto(), "https://fastly.picsum.photos/id/848/200/300.jpg?hmac=cNClhUSP4IM6ZT6RTqdeCOLWYEJYBNXaqdflgf_EqD8", body.tipologia(), body.indirizzoSedeLegale(),
                body.indirizzoSedeOperativa());

        return clienteRepository.save(cliente);
    }


    public Page<Cliente> trovaTuttiClienti(int page, int size, String sortby) {
        if (page > 10) page = 10;
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortby));
        return clienteRepository.findAll(pageable);
    }


    public Cliente trovaClienteById(UUID id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }


    public void cancellaClienteById(UUID id) {
        Cliente cliente = trovaClienteById(id);
        clienteRepository.delete(cliente);
    }

}
