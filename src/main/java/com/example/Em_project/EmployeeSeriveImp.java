package com.example.Em_project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.OptimisticLockException;


@Service
public class EmployeeSeriveImp implements EmployeeSerive{
    @Autowired
    private EmployeeRepository employeeRepository;

    //List<Employee> employees = new ArrayList<>(); 

    // @Override
    // public String createEmployee(Employee employee) { 
    //     EmployEntity employEntity = new EmployEntity();
    //     BeanUtils.copyProperties(employee, employEntity);
    //     employeeRepository.save(employEntity);
    //    //employees.add(employee);
    //    return "saved successfully";
    // }


    @Override
    public String createEmployee(Employee employee) {
        try {
            EmployEntity employEntity = new EmployEntity();
            BeanUtils.copyProperties(employee, employEntity);

            if (employEntity.getId() != null) {
                // Fetch existing entity and update only allowed fields
                EmployEntity existingEntity = employeeRepository.findById(employEntity.getId()).orElse(null);
                if (existingEntity != null) {
                    existingEntity.setId(employEntity.getId());
                    existingEntity.setName(employEntity.getName());
                    existingEntity.setEmail(employEntity.getEmail());
                    existingEntity.setPhone(employEntity.getPhone());
                    employEntity = existingEntity; // Use the attached entity
                }
            }

            employeeRepository.save(employEntity);
            return "Saved successfully";
        } catch (OptimisticLockException e) {
            return "Error saving employee: Another transaction modified the entity.";
        } catch (Exception e) {
            return "Error saving employee: " + e.getMessage();
        }
    }


    @Override
    public List<Employee> readEmployees() {
        List<EmployEntity> employList = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();

        for(EmployEntity employEntity : employList) {
            Employee emp = new Employee();
            emp.setId(employEntity.getId());
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
        // return true;
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;

    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        try {
            EmployEntity existingEmployee = employeeRepository.findById(id).get();
            existingEmployee.setName(employee.getName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPhone(employee.getPhone());
            employeeRepository.save(existingEmployee);
            return "Employee Updated successfully";
        }catch(Exception e){
            return "Error update the Employee: " + e.getMessage();
        }
    }


    @Override
    public Employee readEmployee(Long id) {
        EmployEntity employeeEntity = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee;
 
    }

}
