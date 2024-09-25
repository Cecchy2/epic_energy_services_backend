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
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class FattureService {
    @Autowired
    private FattureRepository fatturaRepository;
    @Autowired
    private ClientiService clienteService;

    public Page<FattureRespDTO> findAll(int page, int size, String sortBy) {

        if (page > 150) page = 150;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Fattura> fatturaPage = this.fatturaRepository.findAll(pageable);


        return fatturaPage.map(fattura -> new FattureRespDTO(
                fattura.getId(),
                fattura.getDataFattura(),
                fattura.getImporto(),
                fattura.getNumeroFattura(),
                fattura.getStatoFattura(),
                fattura.getCliente().getId().toString()));

    }

    public Fattura findById(UUID fatturaId) {
        return this.fatturaRepository.findById(fatturaId).orElseThrow(() -> new NotFoundException(fatturaId));
    }

    public FattureRespDTO findByIdResponse(UUID fatturaId) {
        Fattura found = this.findById(fatturaId);

        return new FattureRespDTO(found.getId(), found.getDataFattura(), found.getImporto(), found.getNumeroFattura(),
                found.getStatoFattura(), found.getCliente().getId().toString());
    }

    public FattureRespDTO save(NewFatturaDTO body) {
        Cliente foundCliente = this.clienteService.trovaClienteById(UUID.fromString(body.clienteId()));
        Fattura newFattura = new Fattura(body.dataFattura(), body.importo(), foundCliente);

        this.fatturaRepository.save(newFattura);
        FattureRespDTO resp = new FattureRespDTO(newFattura.getId(), body.dataFattura(), body.importo(), newFattura.getNumeroFattura(), newFattura.getStatoFattura(), body.clienteId());
        return resp;
    }

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

    public FattureRespDTO updateStatoFatturaById(UUID fatturaId, UpdateStatoFatturaDTO body) {
        Fattura foundFattura = this.findById(fatturaId);

        foundFattura.setStatoFattura(StatoFattura.valueOf(body.statoFattura()));
        this.fatturaRepository.save(foundFattura);

        FattureRespDTO resp = new FattureRespDTO(foundFattura.getId(), foundFattura.getDataFattura(), foundFattura.getImporto(), foundFattura.getNumeroFattura(), foundFattura.getStatoFattura(), foundFattura.getCliente().getId().toString());
        return resp;
    }


    public void delete(UUID fatturaId) {
        Fattura found = this.findById(fatturaId);

        this.fatturaRepository.delete(found);
    }

    public List<Fattura> getFattureByClienteId(UUID clienteId){
        List<Fattura> fatture = fatturaRepository.findFattureByClienteId(clienteId);
        System.out.println("Fatture trovate " + fatture.size());
        return fatture;
    }

    public List<Fattura> getFattureByStato(StatoFattura statoFattura){
        return fatturaRepository.findFattureByStatoFattura(statoFattura);
    }

    public List<Fattura> getFatturaByDataFattura(LocalDate dataFattura){
        return fatturaRepository.findFattureBydataFattura(dataFattura);
    }

    public List<Fattura> getFatturaByAnno(int anno){
        LocalDate startDate = LocalDate.of(anno, 1, 1);
        LocalDate endDate = LocalDate.of(anno, 12, 31);
        return fatturaRepository.findByDataFatturaBetween(startDate, endDate);
    }


}
