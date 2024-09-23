package epic_team6.buildweek_epic_energy_services.services;

import epic_team6.buildweek_epic_energy_services.entities.Provincia;
import epic_team6.buildweek_epic_energy_services.exceptions.NotFoundException;
import epic_team6.buildweek_epic_energy_services.repositories.ProvincieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProvinceService {
    @Autowired
    private ProvincieRepository provincieRepository;


    public List<Provincia> saveAll(List<Provincia> province) {
        return provincieRepository.saveAll(province);
    }

    public Provincia findByNome(String nome) {
        return this.provincieRepository.findByNome(nome);
    }

    public Provincia findById(UUID id){
        return this.provincieRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Page<Provincia> findAll(int page, int size, String sortBy) {
        if (page > 100) page = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.provincieRepository.findAll(pageable);
    }
}
