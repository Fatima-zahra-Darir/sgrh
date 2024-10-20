/* package controllers;

import views.EmployeView;
import models.Employe;

public class EmployeController {
    private Employe employe;
    private EmployeView employeView;

    public EmployeController(Employe employe) {
        this.employe = employe;
        this.employeView = new EmployeView(employe.getTypeEmploye());

        // Add listeners to view actions, if needed
        addViewListeners();
    }

    private void addViewListeners() {
        // Example of adding listeners and connecting them to model actions
        employeView.addRequestLeaveListener(e -> requestLeave());
        if ("Manager".equals(employe.getTypeEmploye())) {
            employeView.addManageTeamListener(e -> manageTeam());
        } else if ("Directeur".equals(employe.getTypeEmploye())) {
            employeView.addViewDepartmentPerformanceListener(e -> viewDepartmentPerformance());
        } else if ("Technicien".equals(employe.getTypeEmploye())) {
            employeView.addLogWorkListener(e -> logWork());
        }
    }

    private void requestLeave() {
        // Logic for requesting leave, interacting with the model
        System.out.println("Leave requested by: " + employe.getNom());
    }

    private void manageTeam() {
        // Logic for Manager-specific task: managing team members
        System.out.println("Managing team for manager: " + employe.getNom());
    }

    private void viewDepartmentPerformance() {
        // Logic for Directeur-specific task: viewing department performance
        System.out.println("Viewing performance for department of: " + employe.getNom());
    }

    private void logWork() {
        // Logic for Technicien-specific task: logging work
        System.out.println("Logging work for technician: " + employe.getNom());
    }
}
 */