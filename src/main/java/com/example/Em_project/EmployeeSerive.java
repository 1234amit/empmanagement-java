package com.example.Em_project;

import java.util.List;

public interface EmployeeSerive {
    String createEmployee(Employee employee);
    List<Employee> readEmployees();
    boolean deleteEmployee(Long id);
}
