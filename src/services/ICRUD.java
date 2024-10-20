package services;

import javax.swing.table.DefaultTableModel;

public interface ICRUD {

    // Add a new employee
    void ajouter(String nom , String prenom , String adresse , String telephone , String email  ,  String poste );

    // Update employee
    void mettreAjour(String nom , String prenom , String adresse , String telephone , String email  ,  String poste );

    // Delete a employee
    void supprimer(int id);

    // Fetch all employees
    void afficherTous(DefaultTableModel tableModel);
}