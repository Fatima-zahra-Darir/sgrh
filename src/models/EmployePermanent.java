package models;

import java.util.Date;
public class EmployePermanent  extends Employe {
    public EmployePermanent(String nom, String prenom, Date dateNaissance, String email, 
        String telephone, double salaireBase, Poste poste, String typeEmploye) {
    super(nom, prenom, dateNaissance, email, telephone, salaireBase, poste, typeEmploye);
    }

    @Override
    public double calculerSalaire() {
    // Implémente le calcul du salaire spécifique pour un employé permanent
    return salaireBase; // Exemple simple
    }
}
