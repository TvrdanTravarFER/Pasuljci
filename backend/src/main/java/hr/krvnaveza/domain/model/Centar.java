package hr.krvnaveza.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name="center")
public class Centar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "centerid")
    private int centerId;

    @Column(name = "centername", nullable = false)
    private String naziv;

    @Column(name="address", nullable = false)
    private String adresa;

    protected Centar(){} // JPA required konstruktor

    public Centar(int centerId, String naziv, String adresa) {
        this.centerId = centerId;
        this.naziv = naziv;
        this.adresa = adresa;
    }

    public int getCenterId() {
        return centerId;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getAdresa() {
        return adresa;
    }
}
