package epic_team6.buildweek_epic_energy_services.repositories;

import epic_team6.buildweek_epic_energy_services.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ClientiRepository extends JpaRepository<Cliente, UUID> {
    boolean existsByEmail(String email);

    boolean existsByPartitaIva(String partitaIva);

    /*@Query("SELECT c FROM Cliente c WHERE " +
            "(:minFatturato IS NULL OR c.fatturatoAnnuale >= :minFatturato) AND " +
            "(:maxFatturato IS NULL OR c.fatturatoAnnuale <= :maxFatturato) AND " +
            "(:inizioData IS NULL OR c.dataInserimento >= :inizioData) AND " +
            "(:fineData IS NULL OR c.dataInserimento <= :fineData) AND " +
            "(:inizioDataContatto IS NULL OR c.dataUltimoContatto >= :inizioDataContatto) AND " +
            "(:fineDataContatto IS NULL OR c.dataUltimoContatto <= :fineDataContatto) AND " +
            "(LOWER(c.nomeContatto) LIKE LOWER(CONCAT('%', :parteNome, '%')))")
    List<Cliente> findByFilters(
            @Param("minFatturato") double minFatturato,
            @Param("maxFatturato") double maxFatturato,
            @Param("inizioData") LocalDate inizioDataInserimento,
            @Param("fineData") LocalDate fineDataInserimento,
            @Param("inizioDataContatto") LocalDate inizioDataContatto,
            @Param("fineDataContatto") LocalDate fineDataContatto,
            @Param("parteNome") String parteNome
    );*/

    //FILTRA PER FATTURATO ANNUALE
    @Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale BETWEEN :minFatturato AND :maxFatturato")
    List<Cliente> findByFatturatoAnnuale(@Param("minFatturato") double minFatturato, @Param("maxFatturato") double maxFatturato);

    //FILTRA PER DATA INSERIMENTO
    @Query("SELECT c FROM Cliente c WHERE c.dataInserimento BETWEEN :inizioDataInserimento AND :fineDataInserimento")
    List<Cliente> findByDataInserimento(@Param("inizioDataInserimento") LocalDate inizioDataInserimento, @Param("fineDataInserimento") LocalDate fineDataInserimento);

    //FILTRA PER DATA ULTIMO CONTATTO
    @Query("SELECT c FROM Cliente c WHERE c.dataUltimoContatto BETWEEN :inizioDataContatto AND :fineDataContatto")
    List<Cliente> findByDataUltimoContatto(@Param("inizioDataContatto") LocalDate inizioDataContatto, @Param("fineDataContatto") LocalDate fineDataContatto);

    //FILTRA PER PARTE DEL NOME
    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nomeContatto) LIKE LOWER(CONCAT('%', :parteNome, '%'))")
    List<Cliente> findByParteDelNome(@Param("parteNome") String parteNome);

}
