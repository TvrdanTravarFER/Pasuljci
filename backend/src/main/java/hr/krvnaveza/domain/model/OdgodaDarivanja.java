package hr.krvnaveza.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table
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

    protected OdgodaDarivanja(){} // JLA stvar

    public OdgodaDarivanja(long odgodaId, Date vrijediDo, String razlog, boolean trajna) {
        this.odgodaId = odgodaId;
        this.trajna = trajna;
        this.razlog = razlog;
        this.vrijediDo = vrijediDo;
    }
}
