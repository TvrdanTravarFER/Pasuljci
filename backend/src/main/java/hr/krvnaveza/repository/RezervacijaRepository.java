package hr.krvnaveza.repository;

import hr.krvnaveza.domain.model.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {
    Optional<Rezervacija> findByQrToken(UUID token);
    List<Rezervacija> findByCentar(int centerId);
}
