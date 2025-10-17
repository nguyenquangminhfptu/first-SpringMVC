package org.Irepository;

import org.dao.Department;

import java.util.List;

public interface IDepartmentRepository {
    public List<Department> getAllDepartments();
    public Department getDepartmentsById(int id);
    public boolean addDepartment(Department department);
    public boolean updateDepartment(Department department);
    public boolean deleteDepartment(int id);

}
