package epic_team6.buildweek_epic_energy_services.repositories;

import epic_team6.buildweek_epic_energy_services.entities.Fattura;
import epic_team6.buildweek_epic_energy_services.enums.StatoFattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface FattureRepository extends JpaRepository<Fattura, UUID> {

    List<Fattura> findFattureByClienteId(@Param("clienteId") UUID clienteId);

    List<Fattura> findFattureByStatoFattura (@Param("statoFattura")StatoFattura statoFattura);

    List<Fattura> findFattureBydataFattura(@Param("dataFattura")LocalDate dataFattura);

    List<Fattura> findByDataFatturaBetween(LocalDate startDate, LocalDate endDate);

    List<Fattura> findByImportoBetween(@Param("minimoImporto") double minimoImporto, @Param("massimoImporto") double massimoImporto );
}
