package hr.krvnaveza.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name="staff")
public class DjelatnikCentra {

    @Id
    @Column(name = "userid")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centerid")
    private Centar centar;


    protected DjelatnikCentra(){} // JPA required konstruktor

    public DjelatnikCentra(long userId, Centar centar) {
        this.userId = userId;
        this.centar = centar;
    }
}
