package org.repository;

import org.Irepository.IEmployeeRepository;
import org.dao.Employee;
import org.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeRepository implements IEmployeeRepository {

    @Autowired
    private EmployeeDao employeeDao;
    @Override
    public boolean addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    @Override
    public Employee getEmployee(String employeeID) {
        return null;
    }

    @Override
    public Employee getEmployee(int employeeID) {
        return employeeDao.getEmployee(employeeID);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(int id) {
        return employeeDao.deleteEmployee(id);
    }
    @Override
    public List<Employee> getAllEmployee() {
        return employeeDao.getAllEmployee();
    }
}
