package com.example.Em_project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSeriveImp implements EmployeeSerive{
    @Autowired
    private EmployeeRepository employeeRepository;
    //List<Employee> employees = new ArrayList<>(); 

    @Override
    public String createEmployee(Employee employee) {

        EmployEntity employEntity = new EmployEntity();
        BeanUtils.copyProperties(employee, employEntity);
        employeeRepository.save(employEntity);
       //employees.add(employee);
       return "saved successfully";
    }

    @Override
    public List<Employee> readEmployees() {
        List<EmployEntity> employList = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();

        for(EmployEntity employEntity : employList) {
            Employee emp = new Employee();
            emp.setName(employEntity.getName());
            emp.setEmail(employEntity.getEmail());
            emp.setPhone(employEntity.getPhone());
            employees.add(emp);
        }

        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        //return employees.removeIf(employee -> employee.getId().equals(id));
        return true;

    }

}
