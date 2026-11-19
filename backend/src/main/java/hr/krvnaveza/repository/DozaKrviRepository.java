package hr.krvnaveza.repository;

import hr.krvnaveza.domain.model.DozaKrvi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DozaKrviRepository extends JpaRepository<DozaKrvi, Long> {
    @Query(value = """
            SELECT *
            FROM BloodDose
            WHERE AppointmentID = :appointmentId
            """, nativeQuery = true)
    Optional<DozaKrvi> pronadiPoRezervaciji(
            @Param("appointmentId") Long appointmentId
    );
    @Query(value = """
            SELECT *
            FROM BloodDose
            WHERE Barcode = :barkod
            """, nativeQuery = true)
    Optional<DozaKrvi> pronadiPoBarkodu(
            @Param("barkod") String barkod
    );

}
