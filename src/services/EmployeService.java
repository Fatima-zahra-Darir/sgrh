package services;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import models.Departement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeService {

    private Connection connection;

    public EmployeService() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void ajouterEmploye(String nom, String prenom, String telephone, String email, String poste, String salaire) {
        try {
            String tableName = determineTableName(poste);
            String query = "INSERT INTO employes (nom, prenom, telephone, email, poste, salaire) VALUES (?, ?, ?, ?, ?, ?)";
    
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, telephone);
            stmt.setString(4, email);
            stmt.setString(5, poste);
            stmt.setString(6, salaire);
            int affectedRows = stmt.executeUpdate();
    
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int insertedId = generatedKeys.getInt(1);
                        System.out.println("New employee ID: " + insertedId);
    
                        String sql = "INSERT INTO " + tableName + " (employe_id) VALUES (?)";
                        PreparedStatement stmt2 = connection.prepareStatement(sql);
                        stmt2.setInt(1, insertedId);
                        stmt2.executeUpdate();
                        stmt2.close();
                    } else {
                        System.out.println("Failed to retrieve the ID of the newly inserted employee.");
                    }
                }
            } else {
                System.out.println("No rows were inserted.");
            }
            stmt.close();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        

    // Method to update an employee
    public void mettreAjourEmploye(int id, String nom, String prenom, String telephone, String email, String poste, String salaire) {
        try {
            String query = "UPDATE employes SET nom = ?, prenom = ?, telephone = ?, email = ?, poste = ?, salaire = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
    
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, telephone);
            stmt.setString(4, email);
            stmt.setString(5, poste);
            stmt.setString(6, salaire);
            stmt.setInt(7, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    // Method to delete an employee
    public void supprimerEmploye(int id) {
        try {
            String[] tables = { "managers", "techniciens", "directeurs" };
            for (String table : tables) {
                String query = "DELETE FROM "+table+" WHERE employe_id = ?";
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                stmt.close();
            }
            String query = "DELETE FROM employes WHERE id = ?";
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                stmt.close(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void afficherTous(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Clear existing rows in the table
    
        try {
            String query = "SELECT * FROM employes";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
    
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String telephone = rs.getString("telephone");
                String email = rs.getString("email");
                String poste = rs.getString("poste");
                String salaire = rs.getString("salaire");
    
                tableModel.addRow(new Object[] { id, nom, prenom, telephone, email, poste, salaire });
            }
    
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private String determineTableName(String poste) {
        switch (poste) {
            case "Manager":
                return "managers";
            case "Technicien":
                return "techniciens";
            case "Directeur":
                return "directeurs";
            default:
                throw new IllegalArgumentException("Poste inconnu: " + poste);
        }
    }

    private String determinePosteByTableName(String tableName) {
        switch (tableName) {
            case "managers":
                return "Manager";
            case "techniciens":
                return "Technicien";
            case "directeurs":
                return "Directeur";
            default:
                throw new IllegalArgumentException("Table inconnue: " + tableName);
        
            }
    } 
}