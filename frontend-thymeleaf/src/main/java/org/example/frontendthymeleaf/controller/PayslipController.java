package org.example.frontendthymeleaf.controller;

import org.example.frontendthymeleaf.model.Payslip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/payslips")
public class PayslipController {

    @Autowired
    private RestTemplate restTemplate;

    // List all Payslips
    @GetMapping
    public String getPayslips(Model model) {
        Payslip[] payslips = restTemplate.getForObject("http://payslip-service/payslips", Payslip[].class);
        model.addAttribute("payslips", payslips);
        model.addAttribute("activePage", "payslips");
        return "payslips";
    }

    // Show add form
    @GetMapping("/add")
    public String showAddPayslipForm(Model model) {
        model.addAttribute("payslip", new Payslip());
        return "add_payslip";
    }

    // Add a new Payslip
    @PostMapping
    public String addPayslip(@ModelAttribute Payslip payslip, Model model) {
        try {
            restTemplate.postForObject("http://payslip-service/payslips", payslip, Payslip.class);
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de l'ajout de la fiche de paie.");
            return "add_payslip";
        }
        return "redirect:/payslips";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditPayslipForm(@PathVariable Long id, Model model) {
        try {
            Payslip payslip = restTemplate.getForObject("http://payslip-service/payslips/" + id, Payslip.class);
            model.addAttribute("payslip", payslip);
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors du chargement de la fiche de paie à modifier.");
        }
        return "edit_payslip";
    }

    // Update a Payslip
    @PostMapping("/update/{id}")
    public String updatePayslip(@PathVariable Long id, @ModelAttribute Payslip payslip) {
        try {
            restTemplate.put("http://payslip-service/payslips/" + id, payslip);
        } catch (Exception e) {
            return "redirect:/payslips/edit/" + id + "?error=true";
        }
        return "redirect:/payslips";
    }

    // Delete a Payslip
    @GetMapping("/delete/{id}")
    public String deletePayslip(@PathVariable Long id) {
        restTemplate.delete("http://payslip-service/payslips/" + id);
        return "redirect:/payslips";
    }
}
