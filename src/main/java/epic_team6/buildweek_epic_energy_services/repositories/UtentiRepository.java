package epic_team6.buildweek_epic_energy_services.repositories;

import epic_team6.buildweek_epic_energy_services.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UtentiRepository extends JpaRepository<Utente, UUID> {
    boolean existsByEmail(String email);

    Optional<Utente> findByEmail(String email);
}
