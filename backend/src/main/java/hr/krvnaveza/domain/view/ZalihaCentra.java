package hr.krvnaveza.domain.view;

import hr.krvnaveza.domain.enums.KrvnaGrupa;
import hr.krvnaveza.domain.enums.StatusZalihe;
import hr.krvnaveza.domain.model.Centar;
import jakarta.persistence.*;

@Entity
@Table(name = "CenterBloodStock")
public class ZalihaCentra {

    private KrvnaGrupa kGrupa;

    private int brojDoza;

    private int ukupnoMl;

    @Column(name = "StockLevel")
    private StatusZalihe razina;

    @Id
    @ManyToOne
    @JoinColumn(name = "centerid")
    private Centar centar;

    protected ZalihaCentra(){}

    public ZalihaCentra(KrvnaGrupa kGrupa, Centar centar) {
        this.kGrupa = kGrupa;
        this.centar = centar;
    }


}
