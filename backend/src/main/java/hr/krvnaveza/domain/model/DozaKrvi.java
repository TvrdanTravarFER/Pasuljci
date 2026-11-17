package hr.krvnaveza.domain.model;

import hr.krvnaveza.domain.enums.StatusDoze;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;

import java.util.Date;

@Entity
@Table(name="BloodDose")
public class DozaKrvi {

    @Id
    @Column(name = "DoseID")
    private long doseId;

    @Column (name = "Barcode")
    private String barkod;

    @Column (name = "AmountML")
    private int kolicinaMl;

    @Column (name = "TimeOfCollection")
    private Date vrijemePrikupljanja;

    @Column (name = "ExpirationDate")
    private Date rokTrajanja;

    @Column (name = "DoseStatus")
    private StatusDoze status;

    @Column (name = "TestResult")
    private boolean rezultatTestiranja;

    protected DozaKrvi(){} //JPA stvar

    @PrePersist
    @PreUpdate
    private void provjeriConstraints(){
        if (kolicinaMl <= 0 ){
            throw new IllegalStateException("Doza mora biti veca od 0");
        }
        if (rokTrajanja.before(vrijemePrikupljanja) ){
            throw new IllegalStateException("Datum Isteka mora biti nakon dana prikupljanja");
        }
    }

    public DozaKrvi(long doseId, String barkod, int kolicinaMl, Date vrijemePrikupljanja, Date rokTrajanja, StatusDoze status, boolean rezultatTestiranja) {
        this.doseId = doseId;
        this.barkod = barkod;
        this.kolicinaMl = kolicinaMl;
        this.vrijemePrikupljanja = vrijemePrikupljanja;
        this.rokTrajanja = rokTrajanja;
        this.status = status;
        this.rezultatTestiranja = rezultatTestiranja;
    }

    public void posaljiNaTestiranje(){}

    public void staviNaZalihu(){}

    public void izdajBolnici(){}

    public void odbaci(String razlog){}

}
