package models;

import java.util.Date;

public class Directeur extends Employe {
    private double prime;

    public Directeur(String nom, String prenom, Date dateNaissance, String email,
            String telephone, double salaireBase, double bonus, Poste poste) {
        super(nom, prenom, dateNaissance, email, telephone, salaireBase, poste, telephone);
        this.prime = prime;
    }

    @Override
    public double calculerSalaire() {
        return salaireBase + prime;
    }

    public double getPrime() {
        return prime;
    }

    public void setPrime(double prime) {
        this.prime = prime;
    }
}
