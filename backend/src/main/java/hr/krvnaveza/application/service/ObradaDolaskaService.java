package hr.krvnaveza.application.service;

import hr.krvnaveza.domain.model.Rezervacija;
import hr.krvnaveza.repository.RezervacijaRepository;

import java.util.Optional;
import java.util.UUID;

public class ObradaDolaskaService {

    private RezervacijaRepository rezervacijaRepository;

    public Rezervacija obradiQR(UUID token){

        Optional<Rezervacija> rezervacija = rezervacijaRepository.findByQrToken(token);
        return rezervacija.orElse(null);
    }

    public void evidentirajNedolazak(long id){
        Rezervacija rezervacija = rezervacijaRepository.findById(id).orElse(null);
        if (rezervacija == null) {
            throw new NullPointerException("Ne postoji rezervacija s tim ID");
        }
        rezervacija.oznaciNedolazak();
    }

    public void odbijNaPregledu(long id, String razlog){
        Rezervacija rezervacija = rezervacijaRepository.findById(id).orElse(null);
        if (rezervacija == null) {
            throw new NullPointerException("Ne postoji rezervacija s tim ID");
        }
        rezervacija.odbijNaPregledu(razlog);
    }

    public void zavrsiDarivanje(long id){
        Rezervacija rezervacija = rezervacijaRepository.findById(id).orElse(null);
        if (rezervacija == null) {
            throw new NullPointerException("Ne postoji rezervacija s tim ID");
        }
        rezervacija.zavrsiDarivanje();
    }

}
