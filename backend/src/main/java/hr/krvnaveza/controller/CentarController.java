package hr.krvnaveza.controller;

import hr.krvnaveza.domain.dto.CentarResponse;
import hr.krvnaveza.domain.dto.TerminResponse;
import hr.krvnaveza.repository.CentarRepository;
import hr.krvnaveza.repository.RezervacijaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centri")
public class CentarController {
    private final CentarRepository repository;
    private final RezervacijaRepository rezervacijaRepository;

    public CentarController(CentarRepository repository, RezervacijaRepository rezervacijaRepository) {
        this.repository = repository;
        this.rezervacijaRepository = rezervacijaRepository;
    }

    // GET /api/centri
    @GetMapping
    public List<CentarResponse> dohvatiSveCentre() {
        return repository.findAll().stream().map(
                centar -> new CentarResponse(
                        centar.getCenterId(),
                        centar.getNaziv(),
                        centar.getAdresa()
                )
        ).toList();
    }

    // GET /api/centri/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CentarResponse> dohvatiCentar(@PathVariable int id){
        var optCentar = repository.findById((long) id);

        if (optCentar.isEmpty())
            return ResponseEntity.notFound().build();

        var centar = optCentar.get();

        CentarResponse res = new CentarResponse(
                centar.getCenterId(),
                centar.getNaziv(),
                centar.getAdresa()
        );

        return ResponseEntity.ok(res);
    }

    // GET /api/centri/{id}/termini
    @GetMapping("/{id}/termini")
    public List<TerminResponse> dohvatiTermine(@PathVariable int id){
        return rezervacijaRepository.findByCentar(id).stream().map(
                rezervacija -> new TerminResponse(
                        rezervacija.getPocetak(),
                        rezervacija.getKraj(),
                        rezervacija.getStatus()
                )
        ).toList();
    }

    // GET /api/centri/{id}/zalihe
    // PUT /api/centri/{id}/radno-vrijeme


}
