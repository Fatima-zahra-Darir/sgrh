package services;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class DepartementService {

    private Connection conn;

    // Constructor to establish the connection
    public DepartementService() {
        this.conn = DatabaseConnection.getConnection();

    }

    // Method to add a new department
    public void ajouterDepartement(String nom, String description) {
        String query = "INSERT INTO departements (nom, description) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, nom);
            ps.setString(2, description);
            ps.executeUpdate();
            System.out.println("Département ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a department
    public void mettreAjourDepartement(int id, String nom, String description) {
        String query = "UPDATE departements SET nom = ?, description = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, nom);
            ps.setString(2, description);
            ps.setInt(3, id);
            ps.executeUpdate();
            System.out.println("Département mis à jour avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a department
    public void supprimerDepartement(int id) {
        String query = "DELETE FROM departements WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Département supprimé avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to fetch and display all departments in the table
    public void afficherTous(DefaultTableModel tableModel) {
        // Clear the existing rows in the table model
        tableModel.setRowCount(0);

        String query = "SELECT id, nom, description FROM departements";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String description = rs.getString("description");

                // Add the row to the table model
                tableModel.addRow(new Object[]{id, nom, description});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
