package org.example.frontendthymeleaf.controller;

import org.example.frontendthymeleaf.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String getEmployees(Model model) {
        Employee[] employees = restTemplate.getForObject("http://employee-service/employees", Employee[].class);
        model.addAttribute("employees", employees);
        model.addAttribute("activePage", "employees");
        return "employees";
    }

    // Afficher le formulaire d'ajout d'un employé
    @GetMapping("/add")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "add_employee";
    }

    // Traiter l'ajout d'un employé
    @PostMapping
    public String addEmployee(@ModelAttribute Employee employee) {
        restTemplate.postForObject("http://employee-service/employees", employee, Employee.class);
        return "redirect:/employees";
    }

    // Afficher le formulaire de modification d'un employé
    @GetMapping("/edit/{id}")
    public String showEditEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = restTemplate.getForObject("http://employee-service/employees/" + id, Employee.class);
        model.addAttribute("employee", employee);
        return "edit_employee";
    }

    // Traiter la modification d'un employé
    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee) {
        restTemplate.put("http://employee-service/employees/" + id, employee);
        return "redirect:/employees";
    }

    // Supprimer un employé
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        restTemplate.delete("http://employee-service/employees/" + id);
        return "redirect:/employees";
    }
}
