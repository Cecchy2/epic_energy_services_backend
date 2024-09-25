package epic_team6.buildweek_epic_energy_services.services;

import epic_team6.buildweek_epic_energy_services.entities.Comune;
import epic_team6.buildweek_epic_energy_services.entities.Indirizzo;
import epic_team6.buildweek_epic_energy_services.entities.Provincia;
import epic_team6.buildweek_epic_energy_services.payloads.IndirizzoDTO;
import epic_team6.buildweek_epic_energy_services.repositories.IndirizziResponsDTO;
import epic_team6.buildweek_epic_energy_services.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IndirizzoService {
    @Autowired
    private IndirizzoRepository indirizzoRepository;
    @Autowired
    private ComuniService comuniService;
    @Autowired
    private ProvinceService provinceService;


    public IndirizziResponsDTO creaIndirizzo(IndirizzoDTO body){
        Comune comune = this.comuniService.findById(UUID.fromString(body.comune_id()));
        Provincia provincia = this.provinceService.findById(UUID.fromString(body.provincia_id()));

        Indirizzo indirizzo= new Indirizzo(body.via(), body.civico(), body.localita(), body.cap(), comune, provincia);
         this.indirizzoRepository.save(indirizzo);
        return new IndirizziResponsDTO(indirizzo.getId());
    }



    public Indirizzo findById(UUID indirizzoId) {
        return this.indirizzoRepository.findById(indirizzoId).orElseThrow();
    }

    /*public Indirizzo saveIndirizzo(IndirizzoDTO body) {
        Indirizzo indirizzo = new Indirizzo(body.via(), body.civico(), body.localita(), body.cap(), body.comune(), body.provincia());
        Indirizzo savedIndirizzo = this.indirizzoRepository.save(indirizzo);
        return savedIndirizzo;
    }*/

    public Page<Indirizzo> findAll(int page, int size, String sortBy) {
        if (page > 100) page = 100;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.indirizzoRepository.findAll(pageable);
    }

    public void findByIdAndDelete(UUID indirizzoId) {
        Indirizzo found = this.findById(indirizzoId);
        this.indirizzoRepository.delete(found);
    }

    public Indirizzo findByIdAndUpdate(UUID indirizzoId, Indirizzo newIndirizzoData) {
        Indirizzo found = this.findById(indirizzoId);
        found.setVia(newIndirizzoData.getVia());
        found.setCivico(newIndirizzoData.getCivico());
        found.setLocalita(newIndirizzoData.getLocalita());
        found.setCap(newIndirizzoData.getCap());
        found.setComune(newIndirizzoData.getComune());
        found.setProvincia(newIndirizzoData.getProvincia());
        return this.indirizzoRepository.save(found);
    }
}
