package com.example.Em_project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
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

    //post mapeing
    @PostMapping("employees")
    public String createEmployee(@RequestBody Employee employee) {
        //employees.add(employee);
       return employeeSerive.createEmployee(employee);        
        // return "Employee added successfully";
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
