package controllers;

import models.User;
import views.LoginView;
import views.AdminView;
import views.ManagerView;
import views.TechnicienView;
import views.DirecteurView;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class LoginController {
    // For simplicity, using a map to store admin user data. Replace this with database queries for real implementations.
    private Map<String, User> users;

    public LoginController() {
        users = new HashMap<>();
        users.put("admin", new User("admin@admin", "admin123", "ADMIN")); // Hardcoded admin credentials
    }

    public void authenticateUser(String username, String password, LoginView loginView) {
        // First, check if the user is an admin
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(loginView, "Login successful! Welcome " + user.getRole());
            loginView.dispose();
            if ("ADMIN".equals(user.getRole())) {
                new AdminView().setVisible(true);
            }
        } else {
            // If not admin, check the database for employee credentials
            loginView.authenticateEmployee(username, password);
        }
    }
}
