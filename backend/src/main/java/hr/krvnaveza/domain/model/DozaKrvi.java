package hr.krvnaveza.domain.model;

import hr.krvnaveza.domain.enums.StatusDoze;
import java.util.Date;

public class DozaKrvi {
    private long doseId;
    private String barkod;
    private int kolicinaMl;
    private Date vrijemePrikupljanja;
    private Date rokTrajanja;
    private StatusDoze status;
    private boolean rezultatTestiranja;

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
