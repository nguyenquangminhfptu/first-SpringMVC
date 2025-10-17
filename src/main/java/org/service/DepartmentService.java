package org.service;

import org.dao.Department;
import org.dao.DepartmentDao;
import org.repository.DepartmentRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepositoty departmentRepositoty;

    public List<Department> getAlDepartments() {
        return departmentRepositoty.getAllDepartments();
    }
    public Department getDepartmentById(int id) {
        return departmentRepositoty.getDepartmentsById(id);
    }
    public boolean addDepartment(Department department) {
        return departmentRepositoty.addDepartment(department);
    }
    public boolean deleteDepartment(int id) {
        return departmentRepositoty.deleteDepartment(id);
    }
    public boolean updateDepartment(Department department) {
        return departmentRepositoty.updateDepartment(department);
    }
}
