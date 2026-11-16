package hr.krvnaveza.application.service;

import hr.krvnaveza.domain.view.ZalihaCentra;
import hr.krvnaveza.domain.model.DozaKrvi;
import java.util.List;

public class EvidencijaDozaService {
    public DozaKrvi evidentirajDozu(long idRezervacije, String barkod){
        return null;
    }

    public void zabiljeziTestiranje(long idDoze, boolean rezultat){}

    public void izdajBolnici(long idDoze){}

    public List<ZalihaCentra> dohvatiZalihe(int idCentra){
        return List.of();
    }
}
