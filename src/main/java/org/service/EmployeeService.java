package org.service;

import org.dao.Employee;
import org.dao.EmployeeDao;
import org.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean addEmployee(Employee employee) {
        return employeeRepository.addEmployee(employee);
    }

    public Employee getEmployee(int id) {
        return employeeRepository.getEmployee(id);
    }

    public boolean updateEmployee(Employee employee) {
        return employeeRepository.updateEmployee(employee);
    }

    public boolean deleteEmployee(int id) {
        return employeeRepository.deleteEmployee(id);
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.getAllEmployee();
    }
}
