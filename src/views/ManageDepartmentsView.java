package views;

import javax.swing.*;
import java.awt.*;

public class ManageDepartmentsView extends JFrame {
    
    public ManageDepartmentsView() {
        setTitle("Manage Departments");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create a main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Add a label
        JLabel label = new JLabel("Department Management", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(label, BorderLayout.NORTH);
        
        // Create a table for department information
        String[] columns = {"Department ID", "Department Name", "Manager"};
        Object[][] data = {}; // Fetch this from the database
        JTable departmentTable = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(departmentTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Add main panel to frame
        add(mainPanel);
    }
    
    public static void main(String[] args) {
        new ManageDepartmentsView().setVisible(true);
    }
}
