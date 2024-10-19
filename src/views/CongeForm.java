package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CongeForm extends JFrame {

    private JTextField dateDebutField, dateFinField, raisonField;
    private JButton logoutBtn, submitBtn;

    public CongeForm() {
        // Window initialization
        setTitle("Demande de Congé");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel for title and logout button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));

        // Logout button
        logoutBtn = new JButton("Déconnecter");
        topPanel.add(logoutBtn, BorderLayout.EAST);

        // Title
        JLabel title = new JLabel("DEMANDE DE CONGÉ", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(title, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // Main panel to hold form
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Form panel for input fields and labels
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        // Labels and input fields
        JLabel dateDebutLabel = new JLabel("Date de Début:");
        dateDebutField = new JTextField(10);
        JLabel dateFinLabel = new JLabel("Date de Fin:");
        dateFinField = new JTextField(10);
        JLabel raisonLabel = new JLabel("Raison de Demande:");
        raisonField = new JTextField(15);

        // Add labels and fields to the form panel
        formPanel.add(dateDebutLabel);
        formPanel.add(dateDebutField);
        formPanel.add(dateFinLabel);
        formPanel.add(dateFinField);
        formPanel.add(raisonLabel);
        formPanel.add(raisonField);

        mainPanel.add(formPanel);

        // Submit Button Panel for submitting the request
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitBtn = new JButton("Soumettre Demande");
        submitPanel.add(submitBtn);
        mainPanel.add(submitPanel);

        add(mainPanel, BorderLayout.CENTER);

        // Attach action listeners to buttons
        addActionListeners();
    }

    // Method to Add Action Listeners to Buttons
    private void addActionListeners() {
        // Submit Button Listener
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dateDebut = dateDebutField.getText();
                String dateFin = dateFinField.getText();
                String raison = raisonField.getText();

                // Validate inputs
                if (dateDebut.isEmpty() || dateFin.isEmpty() || raison.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs!");
                    return;
                }

                // Logic to handle the leave request submission
                // For example, call a method to save the leave request
                JOptionPane.showMessageDialog(null, "Demande de congé soumise:\n" +
                        "Du: " + dateDebut + "\n" +
                        "Au: " + dateFin + "\n" +
                        "Raison: " + raison);

                // Clear fields after submission
                clearFields();
            }
        });

        // Logout Button Listener
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the form and return to the login view
                dispose();
                LoginView loginView = new LoginView();
                loginView.setVisible(true);
            }
        });
    }

    // Method to Clear Input Fields
    private void clearFields() {
        dateDebutField.setText("");
        dateFinField.setText("");
        raisonField.setText("");
    }

    // Main method to launch the Conge Form
    public static void main(String[] args) {
        CongeForm congeForm = new CongeForm();
        congeForm.setVisible(true);
    }
}
