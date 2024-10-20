package services;

import models.Departement; // Assuming you have a Departement model
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartementService {
    private Connection connection;

    public DepartementService() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Get all departements
    public List<Departement> getAllDepartements() {
        List<Departement> departements = new ArrayList<>();
        String query = "SELECT * FROM departement"; // Adjust the table name as needed
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Departement departement = new Departement();
                // Assuming Departement has appropriate setters
                departement.setId(rs.getInt("id"));
                departement.setNom(rs.getString("name"));
                departements.add(departement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departements;
    }

    // Add a new departement
    public void addDepartement(Departement departement) {
        String query = "INSERT INTO departement (name) VALUES (?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, departement.getNom());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing departement
    public void updateDepartement(Departement departement) {
        String query = "UPDATE departement SET name = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, departement.getNom());
            pstmt.setInt(2, departement.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a departement
    public void deleteDepartement(int departementId) {
        String query = "DELETE FROM departement WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, departementId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
