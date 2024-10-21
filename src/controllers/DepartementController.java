/* package controllers;

import models.Departement; // Assuming you have a Departement model
import services.DepartementService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import services.DatabaseConnection;
public class DepartementController {
    private Connection connection;

    public DepartementController() {

        this.connection = DatabaseConnection.getConnection();
    }

    // Get all departements
    public List<Departement> getAllDepartements() {
        DepartementService obj = new DepartementService();
        obj.getAllDepartements();
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
 */