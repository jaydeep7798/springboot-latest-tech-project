package org.example.service;

import org.example.Entity.Employee;
import org.example.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll(); // DB call (mocked in test)
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}

