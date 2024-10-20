package views;

import javax.swing.*;
import controllers.AdminController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView extends JFrame {
    private JButton manageEmployeesButton;
    private JButton manageDepartmentsButton;
    private JButton logoutButton;

    public AdminView() {
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        // Create a main panel with a BorderLayout for centering the buttons
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        // Create a panel for buttons with GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Padding

        // Initialize buttons with styling
        manageEmployeesButton = createStyledButton("Manage Employees");
        manageDepartmentsButton = createStyledButton("Manage Departments");
        logoutButton = createStyledButton("Logout");

        // Add buttons to the button panel
        buttonPanel.add(manageEmployeesButton);
        buttonPanel.add(manageDepartmentsButton);
        buttonPanel.add(logoutButton);

        // Add button panel to main panel
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        
        // Add main panel to the frame
        add(mainPanel);

        // Action listeners for buttons
        manageEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminController().showManageEmployees(AdminView.this);
            }
        });

        manageDepartmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminController().showManageDepatements(AdminView.this);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginView().setVisible(true);
            }
        });
    }

    // Method to create a styled button
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 102, 204)); // Blue color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0)); // Vertical padding
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        return button;
    }

    public static void main(String[] args) throws Exception {
        AdminView data = new AdminView();
        data.setVisible(true);
    }
}
