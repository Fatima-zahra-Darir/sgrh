package models;

import java.util.Date;

public class Conge {
    private Employe employe;
    private Date dateDebut;
    private Date dateFin;
    private String raison;

    public Conge(Employe employe, Date dateDebut, Date dateFin, String raison) {
        this.employe = employe;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.raison = raison;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public String getRaison() {
        return raison;
    }
}
