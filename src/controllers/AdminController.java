package controllers;

import javax.swing.JFrame;

import views.AddDepartements;
import views.AddEmployes;
import views.AddDepartements;
import views.AddEmployes;

public class AdminController {

    public void showManageEmployees(JFrame parentFrame) {
        parentFrame.dispose();
        new AddEmployes().setVisible(true);
    }

    public void manageManagers() {
        // Logic for managing managers
        System.out.println("Managing Managers");
    }

    public void manageTechnicians() {
        // Logic for managing technicians
        System.out.println("Managing Technicians");
    }

    public void manageDirectors() {
        // Logic for managing directors
        System.out.println("Managing Directors");
    }
    public void showManageDepatements(JFrame parentFrame){
        parentFrame.dispose();
        new AddDepartements().setVisible(true);
    }
}