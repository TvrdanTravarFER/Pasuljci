package hr.krvnaveza.application.service;

import hr.krvnaveza.domain.enums.StatusRezervacije;
import hr.krvnaveza.domain.model.Centar;
import hr.krvnaveza.domain.model.Darivatelj;
import hr.krvnaveza.domain.model.Rezervacija;
import hr.krvnaveza.domain.model.TerenskaAkcija;
import hr.krvnaveza.repository.RezervacijaRepository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

public class ObradaDolaskaService {

    private RezervacijaRepository rezervacijaRepository;

    public Rezervacija obradiQR(UUID token){

        Optional<Rezervacija> rezervacija = rezervacijaRepository.findByQrToken(token);
        return rezervacija.orElse(null);
    }

    public UUID upisRezervacije(OffsetDateTime pocetak, OffsetDateTime kraj, StatusRezervacije status, Darivatelj darivatelj, Centar centar, TerenskaAkcija terenskaAkcija){

        UUID qrtoken = UUID.randomUUID();
        rezervacijaRepository.save(new Rezervacija(qrtoken,pocetak,kraj,status,darivatelj,centar,terenskaAkcija));

        return qrtoken;
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
