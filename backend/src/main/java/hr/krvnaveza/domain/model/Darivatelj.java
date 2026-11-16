package hr.krvnaveza.domain.model;

import hr.krvnaveza.domain.converter.KrvnaGrupaConverter;
import hr.krvnaveza.domain.enums.KrvnaGrupa;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name="donor")
public class Darivatelj {

    @Id
    @Column(name = "userid")
    private Long userId;

    @Convert(converter = KrvnaGrupaConverter.class)
    @Column(name = "bloodtype")
    private KrvnaGrupa krvnaGrupa;

    @Column(name = "noshowcount")
    private Integer brojNedolazaka;

    @Column(name = "blockeduntil")
    private OffsetDateTime blokiranDo;

    protected Darivatelj(){} // JPA required konstruktor

    public Darivatelj(Long userId, KrvnaGrupa krvnaGrupa, Integer brojNedolazaka, OffsetDateTime blokiranDo) {
        this.userId = userId;
        this.krvnaGrupa = krvnaGrupa;
        this.brojNedolazaka = brojNedolazaka;
        this.blokiranDo = blokiranDo;
    }
}
