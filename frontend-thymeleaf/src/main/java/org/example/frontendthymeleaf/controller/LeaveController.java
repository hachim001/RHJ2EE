package org.example.frontendthymeleaf.controller;

import org.example.frontendthymeleaf.model.LeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/leaves")
public class LeaveController {

    @Autowired
    private RestTemplate restTemplate;

    // Afficher la liste des congés
    @GetMapping
    public String getLeaves(Model model) {
        LeaveRequest[] leaves = restTemplate.getForObject("http://leave-service/leaves", LeaveRequest[].class);
        model.addAttribute("leaves", leaves);
        model.addAttribute("activePage", "leaves");
        return "leaves";
    }

    // Afficher le formulaire d'ajout d'un congé
    @GetMapping("/add")
    public String showAddLeaveForm(Model model) {
        model.addAttribute("leaveRequest", new LeaveRequest());
        return "add_leave";
    }

    // Traiter l'ajout d'un congé
    @PostMapping
    public String addLeave(@ModelAttribute LeaveRequest leaveRequest) {
        restTemplate.postForObject("http://leave-service/leaves", leaveRequest, LeaveRequest.class);
        return "redirect:/leaves";
    }

    // Afficher le formulaire de modification d'un congé
    @GetMapping("/edit/{id}")
    public String showEditLeaveForm(@PathVariable Long id, Model model) {
        LeaveRequest leaveRequest = restTemplate.getForObject("http://leave-service/leaves/" + id, LeaveRequest.class);
        model.addAttribute("leaveRequest", leaveRequest);
        return "edit_leave";
    }

    // Traiter la modification d'un congé
    @PostMapping("/update/{id}")
    public String updateLeave(@PathVariable Long id, @ModelAttribute LeaveRequest leaveRequest) {
        restTemplate.put("http://leave-service/leaves/" + id, leaveRequest);
        return "redirect:/leaves";
    }

    // Supprimer un congé
    @GetMapping("/delete/{id}")
    public String deleteLeave(@PathVariable Long id) {
        restTemplate.delete("http://leave-service/leaves/" + id);
        return "redirect:/leaves";
    }
}
