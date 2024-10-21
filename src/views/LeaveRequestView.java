// package views;

// import javax.swing.*;
// import javax.swing.table.DefaultTableModel;
// import services.LeaveRequestService;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.sql.Connection;
// import services.DatabaseConnection;


// public class LeaveRequestView extends JFrame {
//     private JTextField employeeIdField, startDateField, endDateField, reasonField;
//     private JButton submitRequestBtn, approveBtn, rejectBtn;
//     private JTable leaveTable;
//     private DefaultTableModel leaveTableModel;
//     private LeaveRequestService leaveRequestService;

//     public LeaveRequestView(LeaveRequestService leaveRequestService) {
//         this.leaveRequestService = leaveRequestService;

//         setTitle("Leave Requests Management");
//         setSize(800, 600);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLayout(new BorderLayout());

//         JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
//         formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

//         formPanel.add(new JLabel("Employee ID:"));
//         employeeIdField = new JTextField(15);
//         formPanel.add(employeeIdField);

//         formPanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
//         startDateField = new JTextField(15);
//         formPanel.add(startDateField);

//         formPanel.add(new JLabel("End Date (YYYY-MM-DD):"));
//         endDateField = new JTextField(15);
//         formPanel.add(endDateField);

//         formPanel.add(new JLabel("Reason:"));
//         reasonField = new JTextField(15);
//         formPanel.add(reasonField);

//         submitRequestBtn = new JButton("Submit Leave Request");
//         formPanel.add(submitRequestBtn);

//         add(formPanel, BorderLayout.NORTH);

//         String[] columns = {"ID", "Employee ID", "Start Date", "End Date", "Reason", "Status"};
//         leaveTableModel = new DefaultTableModel(columns, 0);
//         leaveTable = new JTable(leaveTableModel);
//         leaveRequestService.afficherDemandesCongé(leaveTableModel);

//         JScrollPane scrollPane = new JScrollPane(leaveTable);
//         add(scrollPane, BorderLayout.CENTER);

//         JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//         approveBtn = new JButton("Approve");
//         rejectBtn = new JButton("Reject");
//         buttonPanel.add(approveBtn);
//         buttonPanel.add(rejectBtn);
//         add(buttonPanel, BorderLayout.SOUTH);

//         addActionListeners();
//     }

//     private void addActionListeners() {
//         submitRequestBtn.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 int employeeId = Integer.parseInt(employeeIdField.getText().trim());
//                 String startDate = startDateField.getText().trim();
//                 String endDate = endDateField.getText().trim();
//                 String reason = reasonField.getText().trim();

//                 leaveRequestService.ajouterDemandeCongé(employeeId, startDate, endDate, reason);
//                 leaveRequestService.afficherDemandesCongé(leaveTableModel);
//                 clearFields();
//             }
//         });

//         approveBtn.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 int selectedRow = leaveTable.getSelectedRow();
//                 if (selectedRow != -1) {
//                     int demandeId = (int) leaveTableModel.getValueAt(selectedRow, 0);
//                     leaveRequestService.mettreAJourStatutCongé(demandeId, "Approved");
//                     leaveRequestService.afficherDemandesCongé(leaveTableModel);
//                 } else {
//                     JOptionPane.showMessageDialog(null, "Veuillez sélectionner une demande à approuver.");
//                 }
//             }
//         });

//         rejectBtn.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 int selectedRow = leaveTable.getSelectedRow();
//                 if (selectedRow != -1) {
//                     int demandeId = (int) leaveTableModel.getValueAt(selectedRow, 0);
//                     leaveRequestService.mettreAJourStatutCongé(demandeId, "Rejected");
//                     leaveRequestService.afficherDemandesCongé(leaveTableModel);
//                 } else {
//                     JOptionPane.showMessageDialog(null, "Veuillez sélectionner une demande à rejeter.");
//                 }
//             }
//         });
//     }

//     private void clearFields() {
//         employeeIdField.setText("");
//         startDateField.setText("");
//         endDateField.setText("");
//         reasonField.setText("");
//     }

//     private Connection connection;

//     public LeaveRequestView(LeaveRequestService leaveRequestService, Connection connection) {
//         this.leaveRequestService = leaveRequestService;
//         this.connection = connection;
//     }
  
// }
