package views;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import services.DepartementService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDepartements extends JFrame {

    private JTextField nomField, descriptionField;
    private JButton logoutBtn, submitBtn, updateBtn, deleteBtn;
    private JTable table;
    private DefaultTableModel tableModel;
    private DepartementService departementService;

    private String selectedId;

    public AddDepartements() {
        departementService = new DepartementService();

        // Window initialization
        setTitle("Admin Department Management");
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
        JLabel title = new JLabel("ADMIN DEPARTMENT MANAGEMENT", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(title, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // Main panel to hold form and table
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Form panel for input fields and labels
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // 3 rows, 2 columns
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Labels and input fields
        formPanel.add(new JLabel("Nom:"));
        nomField = new JTextField(15);
        formPanel.add(nomField);

        formPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField(15);
        formPanel.add(descriptionField);

        mainPanel.add(formPanel);

        // Submit Button Panel for adding a new department
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitBtn = new JButton("Ajouter Département");
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

        // Table to display departments
        String[] columns = {"id", "Nom", "Description"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        // Disable direct table editing
        table.setDefaultEditor(Object.class, null);

        // Populate the table with existing data from the database
        departementService.afficherTous(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));
        mainPanel.add(scrollPane);

        add(mainPanel, BorderLayout.CENTER);

        // Attach action listeners to buttons
        addActionListeners();
    }

    // Method to Add Action Listeners to Buttons
    private void addActionListeners() {
        // Add Department Button Listener
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nomField.getText().trim();
                String description = descriptionField.getText().trim();

                // Check if any input field is empty
                if (nom.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs!");
                    return;
                }

                // Add the new department to the database
                departementService.ajouterDepartement(nom, description);

                // Refresh the table with updated data
                departementService.afficherTous(tableModel);
                clearFields(); // Clear input fields after adding
            }
        });

        // Update Department Button Listener
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String nom = nomField.getText().trim();
                    String description = descriptionField.getText().trim();

                    // Update the selected department in the database
                    int id = Integer.valueOf(selectedId);

                    departementService.mettreAjourDepartement(id, nom, description);

                    // Refresh the table
                    departementService.afficherTous(tableModel);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un département à modifier.");
                }
            }
        });

        // Delete Department Button Listener
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = Integer.valueOf(selectedId);
                    departementService.supprimerDepartement(id); // Delete the department
                    departementService.afficherTous(tableModel); // Refresh the table
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un département à supprimer.");
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
                    nomField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    descriptionField.setText(tableModel.getValueAt(selectedRow, 2).toString());
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
        nomField.setText("");
        descriptionField.setText("");
    }

    // Main method to launch the Admin View for managing departments
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddDepartements addDepartements = new AddDepartements();
            addDepartements.setVisible(true);
        });
    }
}
