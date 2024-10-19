// views/EmployeView.java
package views;

import models.Employe;
import models.Manager;
import models.Directeur;
import models.Technicien;

import javax.swing.*;
import java.awt.*;

public class EmployeView extends JFrame {
    private Employe employeCourant;
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;

    public EmployeView() {
        // this.employeCourant = employe;
        // setTitle("Tableau de bord - " + employe.getClass().getSimpleName());
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout());
        tabbedPane = new JTabbedPane();

        // Onglet Profil
        tabbedPane.addTab("Profil", creerOngletProfil());

        // Onglet Gestion des Congés
        tabbedPane.addTab("Gestion des Congés", creerOngletConge());

        // Onglets spécifiques selon le rôle
        if (employeCourant instanceof Manager) {
            tabbedPane.addTab("Gestion d'Équipe", creerOngletGestionEquipe());
        }
        if (employeCourant instanceof Directeur) {
            tabbedPane.addTab("Performance du Département", creerOngletPerformanceDepartement());
        }
        if (employeCourant instanceof Technicien) {
            tabbedPane.addTab("Journal de Travail", creerOngletJournalTravail());
        }

        mainPanel.add(creerTopBar(), BorderLayout.NORTH);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel);
    }

    private JPanel creerTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(51, 51, 51));
        topBar.setPreferredSize(new Dimension(getWidth(), 50));

        JLabel bienvenueLabel = new JLabel("Bienvenue, " + employeCourant.getNom());
        bienvenueLabel.setForeground(Color.WHITE);
        bienvenueLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        JButton deconnexionButton = new JButton("Déconnexion");
        deconnexionButton.addActionListener(e -> handleDeconnexion());

        topBar.add(bienvenueLabel, BorderLayout.WEST);
        topBar.add(deconnexionButton, BorderLayout.EAST);

        return topBar;
    }

    private JPanel creerOngletProfil() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        ajouterChampProfil(panel, gbc, "ID :", String.valueOf(employeCourant.getId()));
        ajouterChampProfil(panel, gbc, "Nom :", employeCourant.getNom());
        ajouterChampProfil(panel, gbc, "Email :", employeCourant.getEmail());
        ajouterChampProfil(panel, gbc, "Poste :", employeCourant.getPoste().getTitre());
        // ajouterChampProfil(panel, gbc, "Département :", employeCourant.getDepartement().getNom());
        ajouterChampProfil(panel, gbc, "Salaire :", String.format("%.2f", employeCourant.calculerSalaire()));

        return panel;
    }

    private void ajouterChampProfil(JPanel panel, GridBagConstraints gbc, String label, String valeur) {
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel(valeur), gbc);

        gbc.gridy++;
    }

    private JPanel creerOngletConge() {
        JPanel panel = new JPanel(new BorderLayout());

        // Formulaire de demande de congé
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField dateDebutField = new JTextField(10);
        JTextField dateFinField = new JTextField(10);
        JTextArea raisonArea = new JTextArea(3, 20);
        JButton soumettreButton = new JButton("Soumettre");

        soumettreButton.addActionListener(e -> {
            String dateDebut = dateDebutField.getText();
            String dateFin = dateFinField.getText();
            String raison = raisonArea.getText();
            // employeCourant.demanderConge(dateDebut, dateFin, raison);
        });

        formPanel.add(new JLabel("Date de début :"), gbc);
        gbc.gridx = 1;
        formPanel.add(dateDebutField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Date de fin :"), gbc);
        gbc.gridx = 1;
        formPanel.add(dateFinField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Raison :"), gbc);
        gbc.gridx = 1;
        formPanel.add(new JScrollPane(raisonArea), gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        formPanel.add(soumettreButton, gbc);

        panel.add(formPanel, BorderLayout.NORTH);

        return panel;
    }

    private JPanel creerOngletGestionEquipe() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Gestion de l'équipe (Manager uniquement)"));
        // Implémentation des fonctionnalités de gestion d'équipe
        return panel;
    }

    private JPanel creerOngletPerformanceDepartement() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Performance du Département (Directeur uniquement)"));
        // Implémentation de la gestion des performances
        return panel;
    }

    private JPanel creerOngletJournalTravail() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Journal de Travail (Technicien uniquement)"));
        // Implémentation de la gestion du journal de travail
        return panel;
    }

    private void handleDeconnexion() {
        // Implémentation de la déconnexion
        System.out.println("Déconnexion réussie.");
        dispose();
        // Logique pour rediriger vers l'écran de connexion
    }

    public static void main(String[] args) throws Exception {
        EmployeView data = new EmployeView();
        data.setVisible(true);
    }
}
