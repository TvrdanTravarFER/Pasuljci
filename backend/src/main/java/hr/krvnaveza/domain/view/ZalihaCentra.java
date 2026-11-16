package hr.krvnaveza.domain.view;

import hr.krvnaveza.domain.enums.KrvnaGrupa;
import hr.krvnaveza.domain.enums.StatusZalihe;

public class ZalihaCentra {
    private KrvnaGrupa kGrupa;
    private int brojDoza;
    private int ukupnoMl;
    private StatusZalihe razina;

    public ZalihaCentra(KrvnaGrupa kGrupa) {
        this.kGrupa = kGrupa;
    }


}
