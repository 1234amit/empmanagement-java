package com.example.Em_project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class EmpController {
    //List<Employee> employees = new ArrayList<>(); 
    // EmployeeSerive employeeSerive = new EmployeeSeriveImp();     
    //dependency injection
    @Autowired
    EmployeeSerive employeeSerive;  

    @GetMapping("employees")
    public List<Employee> getMethodName() {
        return employeeSerive.readEmployees();
    }

    @GetMapping("employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeSerive.readEmployee(id);
    }


    @PostMapping("employees")
    public String createEmployee( @RequestBody Employee employee) {
        return employeeSerive.createEmployee(employee);        
        
    }

    @PutMapping("employees/{id}")
    public String putMethodName(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeSerive.updateEmployee(id, employee);
    }

    //delete mapeing
    @DeleteMapping("employees/{id}")
    public String deleteEmployee(@PathVariable Long id){
        if(employeeSerive.deleteEmployee(id)){
            return "Employee deleted successfully";
        }else{
            return "Employee not found";
        }
    }
    
}
