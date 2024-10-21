package views;

import controllers.LoginController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import services.DatabaseConnection;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Connection connection;
    private LoginController loginController; 

    public LoginView() {
        // Basic setup of the login view
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize the database connection
        connection = DatabaseConnection.getConnection();
        loginController = new LoginController(); // Initialize the controller

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
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                loginController.authenticateUser(username, password, LoginView.this);
            }
        });
    }

    public void authenticateEmployee(String email, String id) {
        String query = "SELECT * FROM employes WHERE email = ? AND id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("poste");
                launchEmployeeView(role, rs);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void launchEmployeeView(String role, ResultSet rs) throws SQLException {
        if (role.equalsIgnoreCase("Manager")) {
            new ManagerView(rs).setVisible(true);
        } else if (role.equalsIgnoreCase("Directeur")) {
            new DirecteurView(rs).setVisible(true);
        } else if (role.equalsIgnoreCase("Technicien")) {
            new TechnicienView(rs).setVisible(true);
        }
        dispose(); // Close the login view
    }

    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
