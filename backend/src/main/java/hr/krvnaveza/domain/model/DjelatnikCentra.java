package hr.krvnaveza.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="staff")
public class DjelatnikCentra {

    @Id
    @Column(name = "userid")
    private Long userId;


    protected DjelatnikCentra(){} // JPA required konstruktor

    public DjelatnikCentra(long userId) {
        this.userId = userId;
    }
}
