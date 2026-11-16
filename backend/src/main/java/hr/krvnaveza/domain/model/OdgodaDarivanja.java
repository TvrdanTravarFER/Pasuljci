package hr.krvnaveza.domain.model;

import java.util.Date;

public class OdgodaDarivanja {
    private Date vrijediDo;
    private String razlog;
    private boolean trajna;

    public OdgodaDarivanja(Date vrijediDo, String razlog, boolean trajna) {
        this.trajna = trajna;
        this.razlog = razlog;
        this.vrijediDo = vrijediDo;
    }
}
