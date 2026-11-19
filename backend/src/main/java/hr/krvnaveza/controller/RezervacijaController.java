package hr.krvnaveza.controller;

import hr.krvnaveza.domain.dto.RezervacijaResponse;
import hr.krvnaveza.repository.RezervacijaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rezervacije")
public class RezervacijaController {
    private final RezervacijaRepository rezervacijaRepository;

    public RezervacijaController(RezervacijaRepository repo){
        this.rezervacijaRepository = repo;
    }

    // GET /api/rezervacije
    @GetMapping
    public List<RezervacijaResponse> dohvatiSveRezervacije() {
        return rezervacijaRepository.findAll().stream().map(
                rezervacija -> new RezervacijaResponse(
                        rezervacija.getAppointmentId(),
                        rezervacija.getQrToken(),
                        rezervacija.getPocetak(),
                        rezervacija.getKraj(),
                        rezervacija.getStatus(),
                        rezervacija.getDarivatelj().getUserId(),
                        rezervacija.getLokacija())
        ).toList();
    }

    // GET /api/rezervacije/broj
    @GetMapping("/broj")
    public long brojRezervacija(){
        return rezervacijaRepository.count();
    }

    // GET /api/rezervacije/{id}
    @GetMapping("/{id}")
    public ResponseEntity<RezervacijaResponse> dohvatiRezervaciju(@PathVariable Long id){
        var optRezervacija = rezervacijaRepository.findById(id);

        if (optRezervacija.isEmpty())
            return ResponseEntity.notFound().build();

        var rezervacija = optRezervacija.get();

        RezervacijaResponse res = new RezervacijaResponse(
                rezervacija.getAppointmentId(),
                rezervacija.getQrToken(),
                rezervacija.getPocetak(),
                rezervacija.getKraj(),
                rezervacija.getStatus(),
                rezervacija.getDarivatelj().getUserId(),
                rezervacija.getLokacija());
        return ResponseEntity.ok(res);
    }


}
