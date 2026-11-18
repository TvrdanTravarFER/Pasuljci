package hr.krvnaveza.application.service;

import hr.krvnaveza.domain.model.Rezervacija;
import hr.krvnaveza.domain.view.ZalihaCentra;
import hr.krvnaveza.domain.model.DozaKrvi;
import hr.krvnaveza.repository.DozaKrviRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class EvidencijaDozaService {

    private DozaKrviRepository dozaKrviRepository;

    public DozaKrvi evidentirajDozu(Long idRezervacije, String barkod){

        Optional<DozaKrvi> dozaKrvi = dozaKrviRepository.pronadiPoRezervaciji(idRezervacije);
        return dozaKrvi.orElse(null);
    }

    public void zabiljeziTestiranje(long idDoze, boolean rezultat){
        DozaKrvi dozaKrvi = dozaKrviRepository.findById(idDoze).orElse(null);
        if (dozaKrvi == null) {
            throw new NullPointerException("Ne postoji doza s tim ID");
        }
        dozaKrvi.zabiljeziRezultatTestiranja(rezultat);
    }

    public void izdajBolnici(long idDoze, String bolnica){
        DozaKrvi dozaKrvi = dozaKrviRepository.findById(idDoze).orElse(null);
        if (dozaKrvi == null) {
            throw new NullPointerException("Ne postoji doza s tim ID");
        }
        dozaKrvi.izdajBolnici(bolnica);
    }

    public List<ZalihaCentra> dohvatiZalihe(int idCentra){
        return List.of();
    }
}
