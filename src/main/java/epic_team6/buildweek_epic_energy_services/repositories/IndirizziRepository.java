package epic_team6.buildweek_epic_energy_services.repositories;

import epic_team6.buildweek_epic_energy_services.entities.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IndirizziRepository extends JpaRepository<Indirizzo, UUID> {

}
