package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Manager extends Employe implements Promotion{
    private double bonus;
    private List<Technicien> equipe;

    public Manager(String nom, String prenom, Date dateNaissance, String email,
            String telephone, double salaireBase, double bonus, Poste poste) {
        super(nom, prenom, dateNaissance, email, telephone, salaireBase, poste, telephone);
        this.bonus = bonus;
        this.equipe = new ArrayList<>();
    }

    @Override
    public double calculerSalaire() {
        return salaireBase + bonus;
    }

    public void ajouterTechnicien(Technicien technicien) {
        equipe.add(technicien);
    }

    public List<Technicien> getEquipe() {
        return equipe;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public void promote(double increasePercentage) {
        salaireBase += salaireBase * increasePercentage / 100;
        bonus += bonus * increasePercentage / 100;
    }
    public void approuverConge(Conge demande) {
        System.out.println("Le congé pour " + demande.getEmploye().getNom() + " a été approuvé.");
        // Logique pour approuver le congé
    }
}
