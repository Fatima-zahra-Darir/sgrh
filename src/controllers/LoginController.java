package controllers;


import models.User;
import views.LoginView;
import views.AdminView;
import views.EmployeView;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class LoginController {
    // For simplicity, using a map to store user data. Replace this with database queries for real implementations.
    private Map<String, User> users;

    public LoginController() {
        users = new HashMap<>();
        users.put("admin", new User("admin", "admin123", "ADMIN"));
        users.put("employe", new User("employe", "employe123", "EMPLOYE"));
    }

    public void authenticateUser(String username, String password, LoginView loginView) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(loginView, "Login successful! Welcome " + user.getRole());
            loginView.dispose();
            if ("ADMIN".equals(user.getRole())) {
                new AdminView().setVisible(true);
            } else if ("EMPLOYE".equals(user.getRole())) {
                new EmployeView().setVisible(true);
            }
        } else {
            loginView.displayErrorMessage("Invalid username or password");
        }
    }
}
