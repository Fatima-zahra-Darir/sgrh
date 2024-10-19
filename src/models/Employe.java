package models;

import java.util.Date;

public abstract class Employe {
    protected int id;
    protected String nom;
    protected String prenom;
    protected Date dateNaissance;
    protected String email;
    protected String telephone;
    protected double salaireBase;
    protected Poste poste;
    protected String typeEmploye;

    public Employe(String nom, String prenom, Date dateNaissance, String email, 
                  String telephone, double salaireBase, Poste poste     , String typeEmploye) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.telephone = telephone;
        this.salaireBase = salaireBase;
        this.poste = poste;
        this.typeEmploye = typeEmploye; 
    }

    public abstract double calculerSalaire();

    protected boolean demanderConge(Conge conge) {
		// enregistrement de conger
        return true;
    }

    // Getters and setters
    public String getTypeEmploye() { return typeEmploye;}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public Date getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public double getSalaireBase() { return salaireBase; }
    public void setSalaireBase(double salaireBase) { this.salaireBase = salaireBase; }
    public Poste getPoste() { return poste; }
    public void setPoste(Poste poste) { this.poste = poste; }
}
