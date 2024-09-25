package epic_team6.buildweek_epic_energy_services.repositories;

import epic_team6.buildweek_epic_energy_services.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Repository
public interface ClientiRepository extends JpaRepository<Cliente, UUID> {
    boolean existsByEmail(String email);

    boolean existsByPartitaIva(String partitaIva);

    //FILTRA PER FATTURATO ANNUALE
    @Query("SELECT c FROM Cliente WHERE c.fatturatoAnnuale BETWEEN :minFatturato AND :maxFatturato")
    List<Cliente> findByFatturatoAnnuale(@Param("minFatturato") double minFatturato, @Param("maxFatturato") double maxFatturato);

    //FILTRA PER DATA INSERIMENTO
    @Query("SELECT c FROM Cliente WHERE c.dataInserimento BETWEEN :inizioData AND :fineData")
    List<Cliente> findByDataInserimento(@Param("inizioData") Locale inizioData, @Param("fineData") LocalDate fineData);

    //FILTRA PER DATA ULTIMO CONTATTO
    @Query("SELECT c FROM Cliente WHERE c.dataUltimoContatto BETWEEN :inizioData AND :fineData")
    List<Cliente> findByDataUltimoContatto(@Param("inizioData") LocalDate inizioData, @Param("fineData") LocalDate fineData);

    //FILTRA PER PARTE DEL NOME
    @Query("SELECT c FROM Cliente WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :parteNome, '%'))")
    List<Cliente> findByParteDelNome(@Param("parteNome") String parteNome);
}
