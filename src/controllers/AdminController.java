package controllers;

import views.ManageEmployeesView;
import views.ManageDepartmentsView;
import views.AdminView;
import services.EmployeeService;
import services.DepartmentService;

public class AdminController {
    private EmployeeService employeeService;
    private DepartmentService departmentService;

    public AdminController() {
        this.employeeService = new EmployeeService(); // Initialize the service for employees
        this.departmentService = new DepartmentService(); // Initialize the service for departments
    }

    // Show Manage Employees View
    public void showManageEmployees(AdminView adminView) {
        ManageEmployeesView manageEmployeesView = new ManageEmployeesView(employeeService);
        manageEmployeesView.setVisible(true);
        adminView.dispose(); // Close the admin view
    }

    // Show Manage Departments View
    public void showManageDepartments(AdminView adminView) {
        ManageDepartmentsView manageDepartmentsView = new ManageDepartmentsView(departmentService);
        manageDepartmentsView.setVisible(true);
        adminView.dispose(); // Close the admin view
    }

    // Additional methods for employee and department management can be added here
}
