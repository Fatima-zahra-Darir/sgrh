// package services;

// import javax.swing.table.DefaultTableModel;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// public class LeaveRequestService {
//     private Connection connection;

//     public LeaveRequestService(Connection connection) {
//         this.connection = connection;
//     }

//     // Method to submit a leave request
//     public void ajouterDemandeCongé(int employeeId, String dateDebut, String dateFin, String raison) {
//         String query = "INSERT INTO demandes_conge (employee_id, date_debut, date_fin, raison) VALUES (?, ?, ?, ?)";
//         try (PreparedStatement pstmt = connection.prepareStatement(query)) {
//             pstmt.setInt(1, employeeId);
//             pstmt.setString(2, dateDebut);
//             pstmt.setString(3, dateFin);
//             pstmt.setString(4, raison);
//             pstmt.executeUpdate();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     // Method to fetch all leave requests
//     public void afficherDemandesCongé(DefaultTableModel tableModel) {
//         String query = "SELECT * FROM demandes_conge";
//         try (PreparedStatement pstmt = connection.prepareStatement(query);
//              ResultSet rs = pstmt.executeQuery()) {
//             tableModel.setRowCount(0); // Clear existing data
//             while (rs.next()) {
//                 int id = rs.getInt("id");
//                 int employeeId = rs.getInt("employee_id");
//                 String dateDebut = rs.getString("date_debut");
//                 String dateFin = rs.getString("date_fin");
//                 String raison = rs.getString("raison");
//                 tableModel.addRow(new Object[]{id, employeeId, dateDebut, dateFin, raison});
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     // Method to update leave request status (approve/reject)
//     public void mettreAJourStatutCongé(int demandeId, String statut) {
//         String query = "UPDATE demandes_conge SET statut = ? WHERE id = ?";
//         try (PreparedStatement pstmt = connection.prepareStatement(query)) {
//             pstmt.setString(1, statut);
//             pstmt.setInt(2, demandeId);
//             pstmt.executeUpdate();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
// }
