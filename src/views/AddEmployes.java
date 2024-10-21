package views;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import services.EmployeService;

public class AddEmployes extends JFrame {

    private JTextField nameField, surnameField, phoneField, emailField, salaryField;
    private JComboBox<String> positionComboBox;
    private JButton logoutBtn, submitBtn, updateBtn, deleteBtn;
    private JTable employeeTable;
    private DefaultTableModel employeeTableModel;
    private EmployeService employeService;
    private String selectedId;

    public AddEmployes() {
        employeService = new EmployeService();

        setTitle("Admin Employee Management");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));

        logoutBtn = new JButton("Déconnecter");
        topPanel.add(logoutBtn, BorderLayout.EAST);

        JLabel title = new JLabel("ADMIN EMPLOYEE MANAGEMENT", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(title, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Form panel for input fields and labels
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10)); // 5 rows, 2 columns
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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

        formPanel.add(new JLabel("Salaire:"));
        salaryField = new JTextField(15);
        formPanel.add(salaryField);

        mainPanel.add(formPanel);

        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitBtn = new JButton("Ajouter Employé");
        submitPanel.add(submitBtn);
        mainPanel.add(submitPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        updateBtn = new JButton("Modifier");
        deleteBtn = new JButton("Supprimer");
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        mainPanel.add(buttonPanel);

        String[] columns = {"id", "Nom", "Prénom", "Numéro de téléphone", "Email", "Poste", "Salaire"};
        employeeTableModel = new DefaultTableModel(columns, 0);
        employeeTable = new JTable(employeeTableModel);

        employeeTable.setDefaultEditor(Object.class, null);

        employeService.afficherTous(employeeTableModel);

        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));
        mainPanel.add(scrollPane);

        add(mainPanel, BorderLayout.CENTER);

        addActionListeners();
    }

    private void addActionListeners() {
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nameField.getText().trim();
                String prenom = surnameField.getText().trim();
                String telephone = phoneField.getText().trim();
                String email = emailField.getText().trim();
                String poste = positionComboBox.getSelectedItem().toString();
                String salaire = salaryField.getText().trim();

                if (nom.isEmpty() || prenom.isEmpty() || telephone.isEmpty() || email.isEmpty() || poste.isEmpty() || salaire.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs!");
                    return;
                }

                employeService.ajouterEmploye(nom, prenom, telephone, email, poste, salaire);

                employeService.afficherTous(employeeTableModel);
                clearFields(); 
            }
        });

        // Update Employee Button Listener
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = employeeTable.getSelectedRow();
                if (selectedRow != -1) {
                    String nom = nameField.getText().trim();
                    String prenom = surnameField.getText().trim();
                    String telephone = phoneField.getText().trim();
                    String email = emailField.getText().trim();
                    String poste = positionComboBox.getSelectedItem().toString();
                    String salaire = salaryField.getText().trim();

                    int id = Integer.valueOf(selectedId);

                    employeService.mettreAjourEmploye(id, nom, prenom, telephone, email, poste, salaire);

                    // Refresh the table
                    employeService.afficherTous(employeeTableModel);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un employé à modifier.");
                }
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = employeeTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = Integer.valueOf(selectedId);
                    employeService.supprimerEmploye(id); // Delete the employee
                    employeService.afficherTous(employeeTableModel); // Refresh the table
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un employé à supprimer.");
                }
            }
        });

        employeeTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && employeeTable.getSelectedRow() != -1) {
                    int selectedRow = employeeTable.getSelectedRow();
                    selectedId = employeeTableModel.getValueAt(selectedRow, 0).toString();
                    nameField.setText(employeeTableModel.getValueAt(selectedRow, 1).toString());
                    surnameField.setText(employeeTableModel.getValueAt(selectedRow, 2).toString());
                    phoneField.setText(employeeTableModel.getValueAt(selectedRow, 3).toString());
                    emailField.setText(employeeTableModel.getValueAt(selectedRow, 4).toString());
                    positionComboBox.setSelectedItem(employeeTableModel.getValueAt(selectedRow, 5).toString());
                    salaryField.setText(employeeTableModel.getValueAt(selectedRow, 6).toString());
                }
            }
        });

        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView loginView = new LoginView(); 
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
        salaryField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddEmployes addEmployes = new AddEmployes();
            addEmployes.setVisible(true);
        });
    }
}
