package views;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class TechnicienView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private final Color BACKGROUND_COLOR = new Color(245, 245, 245);

    // Mock data for demonstration
    private String name = "Lucas Simon";
    private String position = "Technicien";
    private String salary = "3000";

    public TechnicienView() {
        setTitle("Technicien View");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel for title and logout button
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton logoutBtn = new JButton("Déconnecter");
        topPanel.add(logoutBtn, BorderLayout.EAST);
        JLabel title = new JLabel("VUE DU TECHNICIEN", JLabel.CENTER);
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

        // Button panel at the bottom
        JPanel buttonPanel = new JPanel();
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
        new TechnicienView().setVisible(true);
    }
}
