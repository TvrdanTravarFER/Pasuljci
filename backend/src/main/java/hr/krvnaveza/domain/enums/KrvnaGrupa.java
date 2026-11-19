package hr.krvnaveza.domain.enums;

public enum KrvnaGrupa {

    /*
    - spremamo krvnu grupu kao enum, ali nije moguce zapisati +/- u javi kod enuma
    pa treba napisati konverter za vrijednosti baze
    - pogledaj klasu uz domain/converter/KrvnaGrupaConverter.java za bolje razumijevanje
    - p za pozitivnu krvnu grupu, n za negativnu
     */

    Ap("A+"),
    An("A-"),
    Bp("B+"),
    Bn("B-"),
    ABp("AB+"),
    ABn("AB-"),
    Op("O+"),
    On("O-");

    private final String dbValue;

    KrvnaGrupa(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbVal() {
        return dbValue;
    }

    public static KrvnaGrupa convertDBValue(String value){
        for (KrvnaGrupa kg: values()){
            if (kg.dbValue.equals(value)) return kg;
        }
        throw new IllegalArgumentException("Pogrešan unos krvne grupe: " + value);
    }
}
