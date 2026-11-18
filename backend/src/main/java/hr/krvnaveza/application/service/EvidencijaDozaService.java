package hr.krvnaveza.application.service;

import hr.krvnaveza.domain.enums.StatusRezervacije;
import hr.krvnaveza.domain.model.*;
import hr.krvnaveza.domain.view.ZalihaCentra;
import hr.krvnaveza.repository.DozaKrviRepository;
import hr.krvnaveza.repository.ZalihaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EvidencijaDozaService {

    private DozaKrviRepository dozaKrviRepository;
    private ZalihaRepository zalihaRepository;

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

        Optional<List<ZalihaCentra>> zalihaCentra = zalihaRepository.dohvatiPoCentru(idCentra);
        return zalihaCentra.orElse(List.of());
    }
}
