package org.Irepository;

import org.dao.Employee;

import java.util.List;

public interface IEmployeeRepository {
    public boolean addEmployee(Employee employee);
    public Employee getEmployee(String employeeID);

    Employee getEmployee(int employeeID);

    public boolean updateEmployee(Employee employee);

    public boolean deleteEmployee(int id ) ;
    public List<Employee> getAllEmployee();
}
