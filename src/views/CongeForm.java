package views;

import services.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CongeForm extends JFrame {
    private JTextField dateDebutField, dateFinField;
    private JTextArea raisonField;
    private JButton logoutBtn, submitBtn;
    private String employeeId;
    // private String employeeName;

    public CongeForm( ) {
         
    }
    public CongeForm(String employeeId/* , String employeeName */) {
        this.employeeId = employeeId;
        // this.employeeName = employeeName;

        initializeComponents();
        setupLayout();
        addActionListeners();
    }

    private void initializeComponents() {
        setTitle("Demande de Congé");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        dateDebutField = new JTextField(10);
        dateFinField = new JTextField(10);
        raisonField = new JTextArea(4, 30);
        logoutBtn = new JButton("Fermer");
        submitBtn = new JButton("Soumettre Demande");
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel title = new JLabel("DEMANDE DE CONGÉ", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(title, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        addFormRow(formPanel, gbc, "Date de Début (dd/mm/yyyy):", dateDebutField);
        addFormRow(formPanel, gbc, "Date de Fin (dd/mm/yyyy):", dateFinField);
        addFormRow(formPanel, gbc, "Raison:", new JScrollPane(raisonField));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(submitBtn);
        buttonPanel.add(logoutBtn);

        add(topPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, String labelText, Component component) {
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(component, gbc);
    }

    private void addActionListeners() {
        submitBtn.addActionListener(e -> submitLeaveRequest());
        logoutBtn.addActionListener(e -> dispose());
    }

    private void submitLeaveRequest() {
        if (!validateFields()) {
            JOptionPane.showMessageDialog(this,
                    "Veuillez remplir tous les champs correctement",
                    "Erreur de validation",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO conges (employe_id, date_debut, date_fin,raison, statut) VALUES (?, ?, ?, ?,'en_attente')";

            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, employeeId);
            pstmt.setString(2, dateDebutField.getText());
            pstmt.setString(3, dateFinField.getText());
            pstmt.setString(4, raisonField.getText());

            int result = pstmt.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this,
                        "Demande soumise avec succès!",
                        "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erreur lors de la soumission: " + ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {
        return !dateDebutField.getText().trim().isEmpty() &&
               !dateFinField.getText().trim().isEmpty() &&
               !raisonField.getText().trim().isEmpty();
    }
    public static void main(String[] args) throws Exception {
        CongeForm data = new CongeForm("22");
        data.setVisible(true);
    }
}
