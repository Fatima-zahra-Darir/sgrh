package views;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import models.Departement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import services.EmployeService;

public class AddEmployes extends JFrame {

    private JTextField nameField, surnameField, phoneField, emailField;
    private JComboBox<String> positionComboBox;
    private JComboBox<Departement> positionComboBox1;

    private JButton logoutBtn, submitBtn, updateBtn, deleteBtn;
    private JTable table;
    private DefaultTableModel tableModel;
    private EmployeService employeService;

    private String selectedId;

    public AddEmployes() {
        employeService = new EmployeService();

        // Window initialization
        setTitle("Admin Employee Management");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel for title and logout button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));

        // Logout button
        logoutBtn = new JButton("Déconnecter");
        topPanel.add(logoutBtn, BorderLayout.EAST);

        // Title
        JLabel title = new JLabel("ADMIN EMPLOYEE MANAGEMENT", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(title, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // Main panel to hold form and table
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Form panel for input fields and labels
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10)); // 5 rows, 2 columns
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Labels and input fields
        formPanel.add(new JLabel("Nom:"));
        nameField = new JTextField(15);
        formPanel.add(nameField);

        formPanel.add(new JLabel("Prénom:"));
        surnameField = new JTextField(15);
        formPanel.add(surnameField);

        formPanel.add(new JLabel("Numéro de téléphone:"));
        phoneField = new JTextField(15);
        formPanel.add(phoneField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField(15);
        formPanel.add(emailField);

        formPanel.add(new JLabel("Poste:"));
        positionComboBox = new JComboBox<>(new String[]{"Manager", "Technicien", "Directeur"});
        formPanel.add(positionComboBox);

        // formPanel.add(new JLabel("Departement:"));
        // positionComboBox1 = new EmployeService().fetchDep();
        // formPanel.add(positionComboBox1);

        mainPanel.add(formPanel);
        // Submit Button Panel for adding a new employee
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitBtn = new JButton("Ajouter Employé");
        submitPanel.add(submitBtn);
        mainPanel.add(submitPanel);

        // Panel for Update and Delete buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        updateBtn = new JButton("Modifier");
        deleteBtn = new JButton("Supprimer");
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        mainPanel.add(buttonPanel);

        // Table to display employees
        String[] columns = {"id", "Nom", "Prénom", "Numéro de téléphone", "Email", "Poste"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        // Disable direct table editing
        table.setDefaultEditor(Object.class, null);

        // Populate the table with existing data from the database
        employeService.afficherTous(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));
        mainPanel.add(scrollPane);

        add(mainPanel, BorderLayout.CENTER);

        // Attach action listeners to buttons
        addActionListeners();
    }

    // Method to Add Action Listeners to Buttons
    private void addActionListeners() {
        // Add Employee Button Listener
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nameField.getText().trim();
                String prenom = surnameField.getText().trim();
                String telephone = phoneField.getText().trim();
                String email = emailField.getText().trim();
                String poste = positionComboBox.getSelectedItem().toString();

                // Check if any input field is empty
                if (nom.isEmpty() || prenom.isEmpty() || telephone.isEmpty() || email.isEmpty() || poste.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs!");
                    return;
                }

                // Add the new employee to the database
                employeService.ajouterEmploye(nom, prenom, telephone, email, poste);

                // Refresh the table with updated data
                employeService.afficherTous(tableModel);
                clearFields(); // Clear input fields after adding
            }
        });

        // Update Employee Button Listener
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String nom = nameField.getText().trim();
                    String prenom = surnameField.getText().trim();
                    String telephone = phoneField.getText().trim();
                    String email = emailField.getText().trim();
                    String poste = positionComboBox.getSelectedItem().toString();

                    // Update the selected employee in the database
                    int id = Integer.valueOf(selectedId);

                    employeService.mettreAjourEmploye(id, nom, prenom, telephone, email, poste);

                    // Refresh the table
                    employeService.afficherTous(tableModel);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un employé à modifier.");
                }
            }
        });

        // Delete Employee Button Listener
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = Integer.valueOf(selectedId);
                    employeService.supprimerEmploye(id); // Delete the employee
                    employeService.afficherTous(tableModel); // Refresh the table
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un employé à supprimer.");
                }
            }
        });

        // Table Selection Listener to Fill Form with Selected Row Data
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    int selectedRow = table.getSelectedRow();
                    selectedId = tableModel.getValueAt(selectedRow, 0).toString();
                    nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    surnameField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    phoneField.setText(tableModel.getValueAt(selectedRow, 3).toString());
                    emailField.setText(tableModel.getValueAt(selectedRow, 4).toString());
                    positionComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 5).toString());
                }
            }
        });

        // Logout Button Listener
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the admin view
                LoginView loginView = new LoginView(); // Show login view
                loginView.setVisible(true);
            }
        });
    }

    // Method to Clear Input Fields
    private void clearFields() {
        nameField.setText("");
        surnameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        positionComboBox.setSelectedIndex(0);
    }

    // Main method to launch the Admin View
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddEmployes addEmployes = new AddEmployes();
            addEmployes.setVisible(true);
        });
    }
}
