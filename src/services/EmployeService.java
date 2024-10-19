package services;

import models.Employe; // Assuming you have an Employe model
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeService {
    private Connection connection;

    public EmployeService() {
        // Initialize your database connection here
        try {
            // Example connection string (update with your credentials)
            String url = "jdbc:mysql://localhost:3306/your_database";
            String user = "your_username";
            String password = "your_password";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all Employes
    public List<Employe> getAllEmployes() {
        List<Employe> Employes = new ArrayList<>();
        String query = "SELECT * FROM Employes"; // Adjust the table name as needed
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Employe Employe = new Employe();
                // Assuming Employe has appropriate setters
                Employe.setId(rs.getInt("id"));
                Employe.setNom(rs.getString("name"));
                Employe.setPoste(rs.getString("position"));
                Employe.setSalary(rs.getDouble("salary"));
                Employes.add(Employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Employes;
    }

    // Add a new Employe
    public void addEmploye(Employe Employe) {
        String query = "INSERT INTO Employes (name, position, salary) VALUES (?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, Employe.getName());
            pstmt.setString(2, Employe.getPosition());
            pstmt.setDouble(3, Employe.getSalary());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing Employe
    public void updateEmploye(Employe Employe) {
        String query = "UPDATE Employes SET name = ?, position = ?, salary = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, Employe.getName());
            pstmt.setString(2, Employe.getPosition());
            pstmt.setDouble(3, Employe.getSalary());
            pstmt.setInt(4, Employe.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete an Employe
    public void deleteEmploye(int EmployeId) {
        String query = "DELETE FROM Employes WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, EmployeId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
