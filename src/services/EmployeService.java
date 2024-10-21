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

    public void ajouterEmploye(String nom, String prenom, String telephone, String email, String poste) {
        try {
            String tableName = determineTableName(poste);
            String query = "INSERT INTO employes  (nom, prenom, telephone, email,poste) VALUES (?, ?, ?, ?,?)";

            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, telephone);
            stmt.setString(4, email);
            stmt.setString(5, poste);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int insertedId = generatedKeys.getInt(1);
                        System.out.println("New employee ID: " + insertedId);
                        // "Manager", "Technicien", "Directeur"
                        String sql = "INSERT INTO " + tableName
                                + "  (employe_id) VALUES (?)";
                        PreparedStatement stmt2 = connection.prepareStatement(sql);
                        stmt2.setInt(1, insertedId);
                        stmt2.executeUpdate();

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
    // public JComboBox<Departement> fetchDep() {
    //     JComboBox<Departement> comboBox = new JComboBox<>();
    //     try {
    //         String query = "SELECT * FROM departements";
    //         Statement stmt = connection.createStatement();
    //         ResultSet rs = stmt.executeQuery(query);
    //         while (rs.next()) {
    //             String nom = rs.getString("nom");
    //             int id = rs.getInt("id");
    //             comboBox.addItem(new Departement(id, nom)); 
    //         }
    //         rs.close();
    //         stmt.close();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return comboBox;
    // }
    

    // Method to update an employee
    public void mettreAjourEmploye(int id, String nom, String prenom, String telephone, String email, String poste) {
        try {
            String query = "UPDATE employes SET nom = ?, prenom = ?, telephone = ?, email = ? ,poste=? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, telephone);
            stmt.setString(4, email);
            stmt.setString(5, poste);

            stmt.setInt(6, id);
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

    // Method to display all employees in the table
    public void afficherTous(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Clear existing rows in the table

        try {
            String[] tables = { "managers", "techniciens", "directeurs" };
            // for (String table : tables) {
            String query = "SELECT * FROM employes ";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String telephone = rs.getString("telephone");
                String email = rs.getString("email");
                String poste = rs.getString("poste");
                tableModel.addRow(new Object[] { id, nom, prenom, telephone, email, poste });
            }
            rs.close();
            stmt.close();
            // }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Utility method to determine the table name based on the poste
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

    // Utility method to determine the poste based on the table name
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