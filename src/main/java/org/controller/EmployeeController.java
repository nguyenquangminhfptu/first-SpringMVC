package org.controller;

import org.dao.Employee;
import org.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Trang chính: bảng + hàng đầu để add / edit inline
    @GetMapping("")
    public String list(@RequestParam(value = "editId", required = false) Integer editId, Model model) {
        List<Employee> list = employeeService.getAllEmployees();
        model.addAttribute("employees", list);

        // Nếu có editId → nạp employee vào form inline; ngược lại form rỗng cho Add
        Employee formModel = (editId != null) ? employeeService.getEmployeeById(editId) : new Employee();
        model.addAttribute("formEmployee", formModel);
        model.addAttribute("isEditing", editId != null);

        return "employee-list";
    }

    // Lưu (Add hoặc Update) tuỳ theo id=0 hay id>0
    @PostMapping("/save")
    public String save(@ModelAttribute("formEmployee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    // Xoá
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}
