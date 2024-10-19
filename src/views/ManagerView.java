package views;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class ManagerView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    // Mock data for demonstration
    private String name = "Alice Dupont";
    private String position = "Manager";
    private String salary = "5000";

    public ManagerView() {
        setTitle("Manager View");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel for title and logout button
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton logoutBtn = new JButton("Déconnecter");
        topPanel.add(logoutBtn, BorderLayout.EAST);
        JLabel title = new JLabel("VUE DU MANAGER", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(title, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Table for displaying user info
        String[] columns = {"Nom", "Poste", "Salaire"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);
        
        // Load current user's data
        loadData();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Button panel at the bottom with margin adjustments
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton openFormBtn = new JButton("Remplir Demande");
        buttonPanel.add(openFormBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for the button
        openFormBtn.addActionListener(e -> new CongeForm().setVisible(true));

        // Logout functionality
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginView().setVisible(true);
        });
    }

    private void loadData() {
        // Load current user's data into the table
        tableModel.addRow(new String[]{name, position, salary});
    }

    public static void main(String[] args) {
        new ManagerView().setVisible(true);
    }
}
