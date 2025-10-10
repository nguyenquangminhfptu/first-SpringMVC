package org.service;

import org.dao.Employee;
import org.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeDao.findById(id);
    }

    public void deleteEmployee(int id) {
        employeeDao.delete(id);
    }

    public Employee saveEmployee(Employee employee) {
        // Chuẩn hoá dữ liệu cơ bản
        if (employee.getName() != null) employee.setName(employee.getName().trim());
        if (employee.getDepartment() != null) employee.setDepartment(employee.getDepartment().trim());

        // Chỉ chấp nhận URL bắt đầu bằng http/https; nếu rỗng thì để null
        String url = employee.getImageUrl();
        if (url != null && !url.isBlank()) {
            url = url.trim();
            if (!(url.startsWith("http://") || url.startsWith("https://"))) {
                // nếu không hợp lệ → bỏ qua
                url = null;
            }
        } else {
            url = null;
        }
        employee.setImageUrl(url);

        return employeeDao.save(employee);
    }
}
