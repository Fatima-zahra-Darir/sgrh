package models;

import java.util.Date;

public class Technicien extends Employe {
    private int heuresSup;
    private double tauxHoraire;

    public Technicien(String nom, String prenom, Date dateNaissance, String email, 
                     String telephone, double salaireBase, double tauxHoraire, Poste poste) {
        super(nom, prenom, dateNaissance, email, telephone, salaireBase, poste, telephone);
        this.tauxHoraire = tauxHoraire;
    }

    @Override
    public double calculerSalaire() {
        return salaireBase + (heuresSup * tauxHoraire);
    }

    public int getHeuresSup() {
        return heuresSup;
    }

    public void setHeuresSup(int heuresSup) {
        this.heuresSup = heuresSup;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

}
