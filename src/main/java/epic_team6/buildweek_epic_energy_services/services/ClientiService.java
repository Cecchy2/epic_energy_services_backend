package epic_team6.buildweek_epic_energy_services.services;

import epic_team6.buildweek_epic_energy_services.entities.Cliente;
import epic_team6.buildweek_epic_energy_services.entities.Cliente;
import epic_team6.buildweek_epic_energy_services.enums.TipologiaCliente;
import epic_team6.buildweek_epic_energy_services.exceptions.BadRequestException;
import epic_team6.buildweek_epic_energy_services.exceptions.NotFoundException;
import epic_team6.buildweek_epic_energy_services.payloads.ClientiPayloadDTO;
import epic_team6.buildweek_epic_energy_services.payloads.UtentiPayloadDTO;
import epic_team6.buildweek_epic_energy_services.repositories.ClientiRepository;
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
public class ClientiService {
    @Autowired
    private ClientiRepository clienteRepository;


    public Cliente salvaCliente(ClientiPayloadDTO body) {
        if (clienteRepository.existsByEmail(body.email()) || clienteRepository.existsByPartitaIva(body.partitaIva())) {
            throw new BadRequestException("Il cliente è già registrato");
        }

        Cliente cliente = new Cliente(body.ragioneSociale(),
                body.partitaIva(), body.email(), LocalDate.now(), body.dataUltimoContatto(), body.fatturatoAnnuale(),
                body.pec(), body.telefono(), body.emailContatto(), body.nomeContatto(), body.cognomeContatto(),
                body.telefonoContatto(), "https://fastly.picsum.photos/id/848/200/300.jpg?hmac=cNClhUSP4IM6ZT6RTqdeCOLWYEJYBNXaqdflgf_EqD8", TipologiaCliente.valueOf(body.tipologia()), body.indirizzoSedeLegale(),
                body.indirizzoSedeOperativa());

        return clienteRepository.save(cliente);
    }

    public Cliente findByIdAndUpdate(UUID clienteId, ClientiPayloadDTO body) {

        Cliente found = this.clienteRepository.findById(clienteId).orElseThrow(() -> new NotFoundException(clienteId));
        if (found == null) throw new NotFoundException(clienteId);
        found.setRagioneSociale(body.ragioneSociale());
        found.setPartitaIva(body.partitaIva());
        found.setEmail(body.email());
        found.setDataUltimoContatto(body.dataUltimoContatto());
        found.setFatturatoAnnuale(body.fatturatoAnnuale());
        found.setPec(body.pec());
        found.setTelefono(body.telefono());
        found.setEmailContatto(body.emailContatto());
        found.setNomeContatto(body.nomeContatto());
        found.setCognomeContatto(body.cognomeContatto());
        found.setTelefonoContatto(body.telefonoContatto());
        found.setTipologia(TipologiaCliente.valueOf(body.tipologia()));
        found.setIndirizzoSedeLegale_id(body.indirizzoSedeLegale());
        found.setIndirizzoSedeOperativa_id(body.indirizzoSedeOperativa());


        return clienteRepository.save(found);
    }


    public Page<Cliente> trovaTuttiClienti(int page, int size, String sortby) {
        if (page > 10) page = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortby));
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

    /*public List<Cliente> findByFilters(Double minFatturato, Double maxFatturato,
                                       LocalDate inizioDataInserimento, LocalDate fineDataInserimento,
                                       LocalDate inizioDataContatto, LocalDate fineDataContatto,
                                       String parteNome) {

        return this.clienteRepository.findByFilters(minFatturato, maxFatturato, inizioDataInserimento,
                fineDataInserimento, inizioDataContatto, fineDataContatto,
                parteNome);
    }*/

    public List<Cliente> findByParteDelNome(String parteNome) {
        return this.clienteRepository.findByParteDelNome(parteNome);
    }

    public List<Cliente> findByFatturatoAnnuale(double minFatturato, double maxFatturato) {
        return this.clienteRepository.findByFatturatoAnnuale(minFatturato, maxFatturato);
    }

    public List<Cliente> findByDataInserimento(LocalDate inizioDataInserimento, LocalDate fineDataInserimento) {
        return this.clienteRepository.findByDataInserimento(inizioDataInserimento, fineDataInserimento);
    }

    public List<Cliente> findByDataUltimoContatto(LocalDate inizioDataContatto, LocalDate fineDataContatto) {
        return this.clienteRepository.findByDataUltimoContatto(inizioDataContatto, fineDataContatto);
    }
}
