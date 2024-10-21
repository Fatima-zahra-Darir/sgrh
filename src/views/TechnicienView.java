package views;

import services.DatabaseConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TechnicienView extends JFrame {
    private JTable personalInfoTable;
    private JTable leaveRequestsTable;
    private DefaultTableModel personalInfoTableModel;
    private DefaultTableModel leaveRequestsTableModel;

    private String id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String poste;

    public TechnicienView(ResultSet rs) throws SQLException {
        this.id = rs.getString("id");
        this.nom = rs.getString("nom");
        this.prenom = rs.getString("prenom");
        this.telephone = rs.getString("telephone");
        this.email = rs.getString("email");
        this.poste = rs.getString("poste");

        setTitle("Technicien - " + nom + " " + prenom);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel personalInfoPanel = createPersonalInfoPanel();
        tabbedPane.addTab("Mes Informations", personalInfoPanel);

        JPanel leaveRequestsPanel = createLeaveRequestsPanel();
        tabbedPane.addTab("Mes Demandes", leaveRequestsPanel);

        add(tabbedPane, BorderLayout.CENTER);

        Timer timer = new Timer(60000, e -> refreshLeaveRequests());
        timer.start();
    }

    private JPanel createPersonalInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columns = {"ID", "Nom", "Prénom", "Téléphone", "Email", "Poste"};
        personalInfoTableModel = new DefaultTableModel(columns, 0);
        personalInfoTable = new JTable(personalInfoTableModel);
        personalInfoTable.setDefaultEditor(Object.class, null);

        loadPersonalData();

        panel.add(new JScrollPane(personalInfoTable), BorderLayout.CENTER);
        return panel;
    }

    private void loadPersonalData() {
        personalInfoTableModel.setRowCount(0);
        personalInfoTableModel.addRow(new Object[]{id, nom, prenom, telephone, email, poste});
    }

    private JPanel createLeaveRequestsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columns = {"Date Début", "Date Fin", "Raison", "Statut", "Réponse Admin"};
        leaveRequestsTableModel = new DefaultTableModel(columns, 0);
        leaveRequestsTable = new JTable(leaveRequestsTableModel);
        leaveRequestsTable.setDefaultEditor(Object.class, null);

        JButton newRequestBtn = new JButton("Nouvelle Demande");
        JButton refreshBtn = new JButton("Actualiser");

        newRequestBtn.addActionListener(e -> new CongeForm(id).setVisible(true));
        refreshBtn.addActionListener(e -> refreshLeaveRequests());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(newRequestBtn);
        buttonPanel.add(refreshBtn);

        panel.add(new JScrollPane(leaveRequestsTable), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void refreshLeaveRequests() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM Conges WHERE employe_id = ? ORDER BY id DESC";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();

            leaveRequestsTableModel.setRowCount(0);
            while (rs.next()) {
                leaveRequestsTableModel.addRow(new Object[]{
                        rs.getString("date_debut"),
                        rs.getString("date_fin"),
                        rs.getString("raison"),
                        getStatusLabel(rs.getString("statut")),
                        rs.getString("admin_response")
                });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String getStatusLabel(String status) {
        switch (status) {
            case "PENDING": return "En attente";
            case "APPROVED": return "Approuvée";
            case "REJECTED": return "Refusée";
            default: return status;
        }
    }
}
