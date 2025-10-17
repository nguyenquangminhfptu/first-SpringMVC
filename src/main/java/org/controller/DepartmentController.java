package org.controller;

import org.dao.Department;
import org.dao.DepartmentDao;
import org.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @GetMapping("/departments")
    public String home(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("list", departmentService.getAlDepartments());//take list
        return "department";
    }
    // 2️⃣ Lấy dữ liệu phòng ban để chỉnh sửa
    @GetMapping("/editDepartment")
    public String editDepartment(@RequestParam("id") int id, Model model) {
        Department dept = departmentService.getDepartmentById(id);
        model.addAttribute("department", dept); // đổ dữ liệu vào form
        model.addAttribute("list", departmentService.getAlDepartments());
        return "department";
    }
    // 3️⃣ Xử lý Add hoặc Update
    @PostMapping("/departmentAction")
    public String saveOrUpdateDepartment(Department department, @RequestParam("action") String action, Model model) {
        switch (action) {
            case "Add":
                department.setId(0);
                departmentService.addDepartment(department);
                break;
            case "Update":
                departmentService.updateDepartment(department);
                break;
        }
        model.addAttribute("department", new Department());
        model.addAttribute("list", departmentService.getAlDepartments());
        return "department";
    }
    // 4️⃣ Xóa phòng ban
    @GetMapping("/deleteDepartment")
    public String deleteDepartment(@RequestParam("id") int id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments"; // redirect để tránh xóa lặp khi F5
    }

}
