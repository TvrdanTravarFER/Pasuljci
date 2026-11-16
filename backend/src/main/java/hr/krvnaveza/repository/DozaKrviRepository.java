package hr.krvnaveza.repository;

import hr.krvnaveza.domain.model.DozaKrvi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DozaKrviRepository extends JpaRepository<DozaKrvi, Long> {
    Optional<DozaKrvi> pronadiPoRezervaciji(Long appointmentId);
}
