package epic_team6.buildweek_epic_energy_services.services;

import epic_team6.buildweek_epic_energy_services.entities.Cliente;
import epic_team6.buildweek_epic_energy_services.entities.Fattura;
import epic_team6.buildweek_epic_energy_services.enums.StatoFattura;
import epic_team6.buildweek_epic_energy_services.exceptions.NotFoundException;
import epic_team6.buildweek_epic_energy_services.payloads.NewFatturaDTO;
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
    public Fattura save(NewFatturaDTO body) {
        Cliente foundCliente = this.clienteService.findById(UUID.fromString(body.clienteId()));
        Fattura newFattura = new Fattura(body.dataFattura(), body.importo(), body.numeroFattura(), StatoFattura.valueOf(body.statoFattura()), foundCliente);

        return this.fatturaRepository.save(newFattura);
    }
}
