package hr.krvnaveza.controller;

import hr.krvnaveza.domain.dto.DozaResponse;
import hr.krvnaveza.domain.enums.StatusDoze;
import hr.krvnaveza.repository.DozaKrviRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doze")
public class DozaKrviController {
    private final DozaKrviRepository repository;

    public DozaKrviController(DozaKrviRepository repository){
        this.repository = repository;
    }

    // GET api/doze
    @GetMapping
    public List<DozaResponse> dohvatiSveDoze(){
        return repository.findAll().stream().map(doza -> new DozaResponse(
                doza.getDoseId(),
                doza.getStatus())).toList();
    }

    // GET api/doze/5
    @GetMapping("/{id}")
    public ResponseEntity<DozaResponse> dohvatiDozu(@PathVariable Long id){
        var optDoza = repository.findById(id);

        if (optDoza.isEmpty())
            return ResponseEntity.notFound().build();

        var doza = optDoza.get();

        DozaResponse res = new DozaResponse(doza.getDoseId(), doza.getStatus());

        return ResponseEntity.ok(res);
    }

    // GET api/doze/broj
    @GetMapping("/broj")
    public long brojDoza() {
        return repository.count();
    }


}
