package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Departement {
    private int id;
    private String nom;
    private List<Employe> employes;

    public Departement(/* int id, String nom */) {
        // this.id = id;
        // this.nom = nom;
        this.employes = new ArrayList<>();
    }

    public void ajouterEmploye(String nom, String prenom, Date dateNaissance, String email, 
    String telephone, double salaireBase, Poste poste     , String typeEmploye) {
    Employe employe = new EmployePermanent( nom,  prenom,   dateNaissance,  email, telephone,   salaireBase, poste ,  typeEmploye);
    employes.add(employe);
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
}
