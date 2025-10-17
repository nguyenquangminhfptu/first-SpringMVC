package org.repository;

import org.Irepository.IDepartmentRepository;
import org.dao.Department;
import org.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DepartmentRepositoty implements IDepartmentRepository {

    @Autowired
    DepartmentDao departmentDao;

    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    @Override
    public Department getDepartmentsById(int id) {
        return departmentDao.getDepartment(id);
    }

    @Override
    public boolean addDepartment(Department department) {
        return departmentDao.addDepartment(department);
    }

    @Override
    public boolean updateDepartment(Department department) {
        return departmentDao.updateDepartment(department);
    }

    @Override
    public boolean deleteDepartment(int id) {
        return departmentDao.deleteDepartment(id);
    }
}
