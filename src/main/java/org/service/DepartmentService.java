package org.service;

import org.dao.Department;
import org.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;

    public List<Department> getAlDepartments() {
        return departmentDao.getDepartments();

    }
}
