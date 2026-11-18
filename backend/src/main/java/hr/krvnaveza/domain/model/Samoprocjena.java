package hr.krvnaveza.domain.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name="selfassessment")
public class Samoprocjena {

    @Id
    @Column(name = "appointmentid")
    private Long appointmentId;

    @Column(name = "submittedat", nullable = false)
    private OffsetDateTime vrijemePredaje;

    @Column(name = "likelyineligible", nullable = false)
    private boolean vjerojatnoNepodoban;

    protected Samoprocjena(){} // JPA req konstruktor

    public Samoprocjena(OffsetDateTime vrijemePredaje, boolean vjerojatnoNepodoban) {
        this.vrijemePredaje = vrijemePredaje;
        this.vjerojatnoNepodoban = vjerojatnoNepodoban;
    }
}
