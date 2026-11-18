package hr.krvnaveza.repository;

import hr.krvnaveza.domain.model.DozaKrvi;
import hr.krvnaveza.domain.view.ZalihaCentra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ZalihaRepository {
    @Query(value = """
            SELECT *
            FROM CenterBloodStock
            WHERE centerid = :centarID
            """, nativeQuery = true)
    Optional<List<ZalihaCentra>> dohvatiPoCentru(
            @Param("centarID") int centarID
    );
}
