package epic_team6.buildweek_epic_energy_services.services;

import epic_team6.buildweek_epic_energy_services.entities.Cliente;
import epic_team6.buildweek_epic_energy_services.entities.Fattura;
import epic_team6.buildweek_epic_energy_services.enums.StatoFattura;
import epic_team6.buildweek_epic_energy_services.exceptions.NotFoundException;
import epic_team6.buildweek_epic_energy_services.payloads.FatturaRespDTO;
import epic_team6.buildweek_epic_energy_services.payloads.NewFatturaDTO;
import epic_team6.buildweek_epic_energy_services.payloads.UpdateStatoFatturaDTO;
import epic_team6.buildweek_epic_energy_services.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FatturaService {
    @Autowired
    private FatturaRepository fatturaRepository;
    @Autowired
    private ClienteService clienteService;

    //FIND ALL CON PAGINAZIONE
    public Page<Fattura> findAll(int page, int size, String sortBy) {

        if (page > 150) page = 150;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return this.fatturaRepository.findAll(pageable);
    }

    //FIND BY ID
    public Fattura findById(UUID fatturaId) {
        return this.fatturaRepository.findById(fatturaId).orElseThrow(() -> new NotFoundException(fatturaId));
    }

    //SAVE DI UNA FATTURA
    public FatturaRespDTO save(NewFatturaDTO body) {
        Cliente foundCliente = this.clienteService.trovaClienteById(UUID.fromString(body.clienteId()));
        Fattura newFattura = new Fattura(body.dataFattura(), body.importo(), foundCliente);

        this.fatturaRepository.save(newFattura);
        FatturaRespDTO resp = new FatturaRespDTO(newFattura.getId(), body.dataFattura(), body.importo(), newFattura.getNumeroFattura(), newFattura.getStatoFattura(), body.clienteId());
        return resp;
    }

    //FIND BY ID AND UPDATE
    public FatturaRespDTO findByIdAndUpdate(UUID fatturaId, NewFatturaDTO body) {
        Fattura foundFattura = this.findById(fatturaId);

        Cliente foundCliente = this.clienteService.trovaClienteById(UUID.fromString(body.clienteId()));

        foundFattura.setDataFattura(body.dataFattura());
        foundFattura.setImporto(body.importo());
        foundFattura.setCliente(foundCliente);

        this.fatturaRepository.save(foundFattura);

        FatturaRespDTO resp = new FatturaRespDTO(foundFattura.getId(), foundFattura.getDataFattura(), foundFattura.getImporto(), foundFattura.getNumeroFattura(), foundFattura.getStatoFattura(), body.clienteId());
        return resp;
    }

    //UPDATE STATO FATTURA
    public FatturaRespDTO updateStatoFatturaById(UUID fatturaId, UpdateStatoFatturaDTO body) {
        Fattura foundFattura = this.findById(fatturaId);

        foundFattura.setStatoFattura(StatoFattura.valueOf(body.statoFattura()));
        this.fatturaRepository.save(foundFattura);

        FatturaRespDTO resp = new FatturaRespDTO(foundFattura.getId(), foundFattura.getDataFattura(), foundFattura.getImporto(), foundFattura.getNumeroFattura(), foundFattura.getStatoFattura(), foundFattura.getCliente().getId().toString());
        return resp;
    }


}
