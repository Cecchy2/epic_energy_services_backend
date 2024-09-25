package epic_team6.buildweek_epic_energy_services.services;

import epic_team6.buildweek_epic_energy_services.entities.Cliente;
import epic_team6.buildweek_epic_energy_services.entities.Fattura;
import epic_team6.buildweek_epic_energy_services.enums.StatoFattura;
import epic_team6.buildweek_epic_energy_services.exceptions.NotFoundException;
import epic_team6.buildweek_epic_energy_services.payloads.FattureRespDTO;
import epic_team6.buildweek_epic_energy_services.payloads.NewFatturaDTO;
import epic_team6.buildweek_epic_energy_services.payloads.UpdateStatoFatturaDTO;
import epic_team6.buildweek_epic_energy_services.repositories.FattureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FattureService {
    @Autowired
    private FattureRepository fatturaRepository;
    @Autowired
    private ClientiService clienteService;

    //FIND ALL CON PAGINAZIONE
    public Page<FattureRespDTO> findAll(int page, int size, String sortBy) {

        if (page > 150) page = 150;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Fattura> fatturaPage = this.fatturaRepository.findAll(pageable);

        //MAPPO LA PAGE<FATTURA> IN UNA PAGE<FatturaRespDTO>
        return fatturaPage.map(fattura -> new FattureRespDTO(
                fattura.getId(),
                fattura.getDataFattura(),
                fattura.getImporto(),
                fattura.getNumeroFattura(),
                fattura.getStatoFattura(),
                fattura.getCliente().getId().toString()));

    }

    //FIND BY ID
    public Fattura findById(UUID fatturaId) {
        return this.fatturaRepository.findById(fatturaId).orElseThrow(() -> new NotFoundException(fatturaId));
    }

    //RESPONSE GET BY ID
    public FattureRespDTO findByIdResponse(UUID fatturaId) {
        Fattura found = this.findById(fatturaId);

        return new FattureRespDTO(found.getId(), found.getDataFattura(), found.getImporto(), found.getNumeroFattura(),
                found.getStatoFattura(), found.getCliente().getId().toString());
    }

    //SAVE DI UNA FATTURA
    public FattureRespDTO save(NewFatturaDTO body) {
        Cliente foundCliente = this.clienteService.trovaClienteById(UUID.fromString(body.clienteId()));
        Fattura newFattura = new Fattura(body.dataFattura(), body.importo(), foundCliente);

        this.fatturaRepository.save(newFattura);
        FattureRespDTO resp = new FattureRespDTO(newFattura.getId(), body.dataFattura(), body.importo(), newFattura.getNumeroFattura(), newFattura.getStatoFattura(), body.clienteId());
        return resp;
    }

    //FIND BY ID AND UPDATE
    public FattureRespDTO findByIdAndUpdate(UUID fatturaId, NewFatturaDTO body) {
        Fattura foundFattura = this.findById(fatturaId);

        Cliente foundCliente = this.clienteService.trovaClienteById(UUID.fromString(body.clienteId()));

        foundFattura.setDataFattura(body.dataFattura());
        foundFattura.setImporto(body.importo());
        foundFattura.setCliente(foundCliente);

        this.fatturaRepository.save(foundFattura);

        FattureRespDTO resp = new FattureRespDTO(foundFattura.getId(), foundFattura.getDataFattura(), foundFattura.getImporto(), foundFattura.getNumeroFattura(), foundFattura.getStatoFattura(), body.clienteId());
        return resp;
    }

    //UPDATE STATO FATTURA
    public FattureRespDTO updateStatoFatturaById(UUID fatturaId, UpdateStatoFatturaDTO body) {
        Fattura foundFattura = this.findById(fatturaId);

        foundFattura.setStatoFattura(StatoFattura.valueOf(body.statoFattura()));
        this.fatturaRepository.save(foundFattura);

        FattureRespDTO resp = new FattureRespDTO(foundFattura.getId(), foundFattura.getDataFattura(), foundFattura.getImporto(), foundFattura.getNumeroFattura(), foundFattura.getStatoFattura(), foundFattura.getCliente().getId().toString());
        return resp;
    }

    //DELETE
    public void delete(UUID fatturaId) {
        Fattura found = this.findById(fatturaId);

        this.fatturaRepository.delete(found);
    }

    public Page<Fattura> filtraFatturaByClienteId(UUID clienteId, Pageable pageable) {
        if (clienteId == null) throw new NotFoundException(clienteId);
        return fatturaRepository.findByClienteId(clienteId, pageable);
    }
}
