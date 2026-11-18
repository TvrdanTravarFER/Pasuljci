package hr.krvnaveza.domain.model;

import hr.krvnaveza.domain.converter.KrvnaGrupaConverter;
import hr.krvnaveza.domain.enums.KrvnaGrupa;
import hr.krvnaveza.domain.enums.StatusDoze;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "BloodDose")
public class DozaKrvi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DoseID")
    private Long doseId;

    @Column(name = "Barcode", nullable = false)
    private String barkod;

    @Column(name = "AmountMl", nullable = false)
    private short kolicinaMl;

    @Column(name = "TimeOfCollection", nullable = false)
    private OffsetDateTime vrijemePrikupljanja;

    @Column(name = "ExpirationDate", nullable = false)
    private OffsetDateTime rokTrajanja;

    @Enumerated(EnumType.STRING)
    @Column(name = "DoseStatus", nullable = false)
    private StatusDoze status = StatusDoze.COLLECTED;

    @Column(name = "TestResult")
    private Boolean rezultatTestiranja;

    @Convert(converter = KrvnaGrupaConverter.class)
    @Column(name = "BloodType", nullable = false)
    private KrvnaGrupa krvnaGrupa;

    @Column(name = "DiscardReason")
    private String razlogOdbacivanja;

    @Column(name = "IssuedTo")
    private String izdanoBolnici;

    @Column(name = "IssuedAt")
    private OffsetDateTime vrijemeIzdavanja;

    @OneToOne
    @JoinColumn(name = "AppointmentID", nullable = false, unique = true)
    private Rezervacija rezervacija;

    @ManyToOne
    @JoinColumn(name = "DonorID", nullable = false)
    private Darivatelj darivatelj;

    @ManyToOne
    @JoinColumn(name = "CenterID", nullable = false)
    private Centar centar;

    @ManyToOne
    @JoinColumn(name = "RecordedBy")
    private DjelatnikCentra evidentirao;

    protected DozaKrvi() {}

    public DozaKrvi(String barkod, short kolicinaMl, OffsetDateTime vrijemePrikupljanja, OffsetDateTime rokTrajanja, KrvnaGrupa krvnaGrupa, Rezervacija rezervacija, Darivatelj darivatelj, Centar centar
    ) {
        this.barkod = barkod;
        this.kolicinaMl = kolicinaMl;
        this.vrijemePrikupljanja = vrijemePrikupljanja;
        this.rokTrajanja = rokTrajanja;
        this.krvnaGrupa = krvnaGrupa;
        this.rezervacija = rezervacija;
        this.darivatelj = darivatelj;
        this.centar = centar;
    }

    public void posaljiNaTestiranje() {
        status = StatusDoze.TESTING;
    }

    public void zabiljeziRezultatTestiranja(boolean rezultat) {
        rezultatTestiranja = rezultat;
    }

    public void staviNaZalihu() {
        status = StatusDoze.STOCK;
    }

    public void izdajBolnici(String bolnica) {
        izdanoBolnici = bolnica;
        vrijemeIzdavanja = OffsetDateTime.now();
        status = StatusDoze.ISSUED;
    }

    public void odbaci(String razlog) {
        razlogOdbacivanja = razlog;
        status = StatusDoze.DISCARDED;
    }

    public Long getDoseId() {
        return doseId;
    }

    public StatusDoze getStatus() {
        return status;
    }
}
