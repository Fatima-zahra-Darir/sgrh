package views;
import javax.swing.*;

import models.Manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginView() {
        // Basic setup of the login view
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to authenticate the user
                boolean authenticated = authenticateUser(usernameField.getText(), String.valueOf(passwordField.getPassword()));

                if (authenticated) {
                    // Show role selection after successful login
                    showRoleSelection();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean authenticateUser(String username, String password) {
        // Authentication logic (e.g., check against database)
        return true; // Replace with actual authentication
    }

    private void showRoleSelection() {
        String[] roles = {"Manager", "Directeur", "Technicien"};
        String selectedRole = (String) JOptionPane.showInputDialog(this, 
                "Select your role:", 
                "Role Selection", 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                roles, 
                roles[0]);

        if (selectedRole != null) {
            // Proceed to the corresponding EmployeeView based on the role
            launchEmployeeView(selectedRole);
        }
    }

    private void launchEmployeeView(String role) {
        // Launch the corresponding EmployeeView based on the role
        if (role.equals("Manager")) {
            // Manager manager= new Manager();
            new ManagerView().setVisible(true);
        } else if (role.equals("Directeur")) {
            new DirecteurView().setVisible(true);
        } else if (role.equals("Technicien")) {
            new TechnicienView().setVisible(true);
        }
        dispose(); // Close the login view
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
