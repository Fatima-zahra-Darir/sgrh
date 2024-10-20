package views;

import javax.swing.*;
import controllers.AdminController;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ManageEmployeesView extends JFrame {
    private JButton managerButton;
    private JButton technicianButton;
    private JButton directorButton;

    public ManageEmployeesView() {
        setTitle("Manage Employees");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        managerButton = createStyledButton("Manage Managers", new Color(0, 102, 204)); // Blue
        technicianButton = createStyledButton("Manage Technicians", new Color(0, 153, 76)); // Green
        directorButton = createStyledButton("Manage Directors", new Color(204, 0, 0)); // Red

        addButtonWithGap(managerButton, 0);
        addButtonWithGap(technicianButton, 1);
        addButtonWithGap(directorButton, 2);

        managerButton.addActionListener(e -> new AdminController().manageManagers());
        technicianButton.addActionListener(e -> new AdminController().manageTechnicians());
        directorButton.addActionListener(e -> new AdminController().manageDirectors());
    }

    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        button.setFocusPainted(false);
        
        // Set padding
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20)); // Top, Left, Bottom, Right
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        return button;
    }

    private void addButtonWithGap(JButton button, int gridY) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.insets = new Insets(10, 0, 10, 0); // Gap between buttons
        add(button, gbc);
    }
}
