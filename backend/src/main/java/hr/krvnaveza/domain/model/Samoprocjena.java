package hr.krvnaveza.domain.model;

import java.util.Date;

public class Samoprocjena {
    private Date vrijemePredaje;
    private boolean vjerojatnoNepodoban;

    public Samoprocjena(Date vrijemePredaje, boolean vjerojatnoNepodoban) {
        this.vrijemePredaje = vrijemePredaje;
        this.vjerojatnoNepodoban = vjerojatnoNepodoban;
    }
}
