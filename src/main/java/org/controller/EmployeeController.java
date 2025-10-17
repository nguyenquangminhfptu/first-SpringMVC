package org.controller;

import jakarta.servlet.http.HttpSession;
import org.dao.Employee;
import org.dao.User;
import org.service.DepartmentService;
import org.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    @GetMapping("/employeeAction")
    public String handleGetDirectAccess(HttpSession session) {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAlDepartments());
        model.addAttribute("list", employeeService.getAllEmployee());
        return "home";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") int id, Model model) {
        // check role
        employeeService.deleteEmployee(id);
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAlDepartments());
        model.addAttribute("list", employeeService.getAllEmployee());
        return "home";
    }

    //giup hien thị thong tin cho nguoi dung sua
    @GetMapping("/edit")
    public String editEmployee(@RequestParam("id") int id, Model model) {
        // check role
        Employee emp = employeeService.getEmployee(id);
        model.addAttribute("employee", emp);
        model.addAttribute("departments", departmentService.getAlDepartments());
        model.addAttribute("list", employeeService.getAllEmployee());
        return "home";
    }

    @PostMapping("/employeeAction")
    public String doaction(Employee employee, @RequestParam("action") String action, Model model,HttpSession session    ) {
        User user = (User) session.getAttribute("user");
        if(user.getRole() !=1) {
            model.addAttribute("role", "employee has no role CRUD");
        }
        else if(user.getRole() ==1) {
            switch (action) {
                case "Add":
                    employee.setId(0);
                    employeeService.addEmployee(employee);
                    break;
                case "Update":
                    employeeService.updateEmployee(employee);
                    break;
            }
        }
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAlDepartments());
        model.addAttribute("list", employeeService.getAllEmployee());

        return "home";
    }

}
