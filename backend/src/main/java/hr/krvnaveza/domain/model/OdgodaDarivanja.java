package hr.krvnaveza.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ManyToAny;

import java.util.Date;

import static java.sql.Types.NULL;

@Entity
@Table(name = "DonorDeferral")
public class OdgodaDarivanja {

    @Id
    @Column(name = "DefferalID")
    private long odgodaId;

    @Column(name = "Until")
    private Date vrijediDo;

    @Column(name = "Reason")
    private String razlog;

    @Column(name = "Infinite")
    private boolean trajna;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "donorid", nullable = false)
    private Darivatelj darivatelj;

    @ManyToOne
    @JoinColumn(name = "appointmentId")
    private Rezervacija rezervacija;

    @ManyToOne
    @JoinColumn(name = "StaffID")
    private DjelatnikCentra djelatnikCentra;


    protected OdgodaDarivanja(){} // JPA stvar

    @PrePersist
    @PreUpdate
    private void provjeriConstraints(){
        if ((!trajna && vrijediDo.equals(new Date(NULL))) || (trajna && !vrijediDo.equals(new Date(NULL)))){
            throw new IllegalStateException("Trajna odgoda treba NULL vrijediDo element");
        }
    }

    public OdgodaDarivanja(long odgodaId, Date vrijediDo, String razlog, boolean trajna, Darivatelj darivatelj, Rezervacija rezervacija, DjelatnikCentra djelatnikCentra) {
        this.odgodaId = odgodaId;
        this.trajna = trajna;
        this.razlog = razlog;
        this.vrijediDo = vrijediDo;
        this.darivatelj = darivatelj;
        this.rezervacija = rezervacija;
        this.djelatnikCentra = djelatnikCentra;
    }
}
