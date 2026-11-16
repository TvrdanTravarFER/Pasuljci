package hr.krvnaveza.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fieldaction")
public class TerenskaAkcija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actionid")
    private Long actionId;

    @Column(name = "actionname", nullable = false)
    private String naziv;

    protected TerenskaAkcija(){} // JPA required konstruktor

    public TerenskaAkcija(long actionId, String naziv) {
        this.actionId = actionId;
        this.naziv = naziv;
    }

}
