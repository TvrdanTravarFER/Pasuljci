package hr.krvnaveza.domain.model;

import hr.krvnaveza.domain.enums.StatusRezervacije;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name="appointment")
public class Rezervacija {

    /*
    Spring Boot setup koji povlaci podatke iz db
    za ostale klase imaj otvoreni schema.sql i po tome ispunjavaj
    sve spring boot stvari kao sto je napravljeno u ovoj klasi

    HIBERNATE: framework dio Spring Boota, povezuje Java objekte s onima u
    PostgreSQL tablici, omogucuje rad s Java objektima umjesto rucnog pisanja SQL
    queryja
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointmentId")
    private Long appointmentId;

    @Column(name = "qrtoken", nullable = false, unique = true)
    private UUID qrToken;

    @Column(name = "appointmentstart", nullable = false)
    private OffsetDateTime pocetak;

    @Column(name = "appointmentend", nullable = false)
    private OffsetDateTime kraj;

    @Enumerated(EnumType.STRING)
    @Column(name = "appointmentstatus", nullable = false)
    private StatusRezervacije status = StatusRezervacije.RESERVED;

    /* SPRING BOOT FETCHTYPE
    FETCHTYPE LAZY: ucitava tek kad je potrebno
    FETCHTYPE EAGER: ucitava odmah, npr. kad Hibernate dohvati rezervaciju
    Hocu li trebati ovaj objekt svaki put kad dohvatim rezervaciju?
    NE ==> LAZY
    DA ==> EAGER

    MANYTOONE: prati dijagram u 4.3 docs te kako se definiraju relacije izmedu
    objekata
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "donorid", nullable = false)
    private Darivatelj darivatelj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centerid")
    private Centar centar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actionid")
    private TerenskaAkcija terenskaAkcija;

    protected Rezervacija() {} // JPA required konstruktor

    /*
    PrePersist - prije unosa entiteta u db se pokrece metoda za provjeru
    PreUpdate - prije updateanja entiteta u db se pokrece metoda za provjeru
    kako znati da se ovo mora dodati? ==> gledaj constraintove u schema.sql
     */
    @PrePersist
    @PreUpdate
    private void validirajLokaciju(){
        if ((centar == null) == (terenskaAkcija == null)){
            throw new IllegalStateException("Rezervacija mora imati jednu lokaciju.");
        }
    }

    public Rezervacija(Long appointmentId, UUID qrToken, OffsetDateTime pocetak, OffsetDateTime kraj, StatusRezervacije status, Darivatelj darivatelj, Centar centar, TerenskaAkcija terenskaAkcija) {
        this.appointmentId = appointmentId;
        this.qrToken = qrToken;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.status = status;
        this.darivatelj = darivatelj;
        this.centar = centar;
        this.terenskaAkcija = terenskaAkcija;
    }

    // getteri i setteri, kada treba implementirati


    // implementirane metode (trebati ce jos dalja implementacija)
    public void oznaciDolazak(){
        this.status = StatusRezervacije.ARRIVED;
    }

    public void oznaciNedolazak(){
        this.status = StatusRezervacije.NOTARRIVED;
    }

    public void odbijNaPregledu(String Razlog){
        this.status = StatusRezervacije.REJECTED;
        //Razlog negdje zapisat jos i guess
    }

    public void zavrsiDarivanje(){
        this.status = StatusRezervacije.FINISHED;
    }
}
