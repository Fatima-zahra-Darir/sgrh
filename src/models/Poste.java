package models;

public class Poste {
    private int id;
    private String titre;
    private String description;
    private double salaireMin;
    private double salaireMax;

    public Poste(int id, String titre, String description, double salaireMin, double salaireMax) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.salaireMin = salaireMin;
        this.salaireMax = salaireMax;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getSalaireMin() { return salaireMin; }
    public void setSalaireMin(double salaireMin) { this.salaireMin = salaireMin; }
    public double getSalaireMax() { return salaireMax; }
    public void setSalaireMax(double salaireMax) { this.salaireMax = salaireMax; }
}
