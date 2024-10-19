package views;

import javax.swing.*;
import java.awt.*;

public class ManageEmployeesView extends JFrame {
    
    public ManageEmployeesView() {
        setTitle("Manage Employees");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create a main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Add a label
        JLabel label = new JLabel("Employee Management", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(label, BorderLayout.NORTH);
        
        // Create a table for employee information
        String[] columns = {"ID", "Name", "Position", "Salary"};
        Object[][] data = {}; // Fetch this from the database
        JTable employeeTable = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Add main panel to frame
        add(mainPanel);
    }
    
    public static void main(String[] args) {
        new ManageEmployeesView().setVisible(true);
    }
}
