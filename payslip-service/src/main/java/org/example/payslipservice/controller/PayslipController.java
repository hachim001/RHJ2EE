package org.example.payslipservice.controller;

import org.example.payslipservice.model.Payslip;
import org.example.payslipservice.service.PayslipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payslips")
public class PayslipController {
    @Autowired
    private PayslipService payslipService;

    @PostMapping
    public Payslip generatePayslip(@RequestBody Payslip payslip) {
        return payslipService.generatePayslip(payslip);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Payslip> getPayslipsByEmployee(@PathVariable Long employeeId) {
        return payslipService.getPayslipsByEmployee(employeeId);
    }

    @GetMapping
    public List<Payslip> getAllPayslips() {
        return payslipService.getAllPayslips();
    }

    @PutMapping("/{id}")
    public Payslip updatePayslip(@PathVariable Long id, @RequestBody Payslip updatedPayslip) {
        // ✅ On utilise findById() de PayslipService
        Payslip existingPayslip = payslipService.findById(id)
                .orElseThrow(() -> new RuntimeException("Fiche de paie introuvable"));

        // ✅ Mise à jour des champs de la fiche de paie
        existingPayslip.setEmployeeId(updatedPayslip.getEmployeeId());
        existingPayslip.setDateIssued(updatedPayslip.getDateIssued());
        existingPayslip.setGrossSalary(updatedPayslip.getGrossSalary());
        existingPayslip.setBonuses(updatedPayslip.getBonuses());
        existingPayslip.setDeductions(updatedPayslip.getDeductions());
        existingPayslip.setNetPay(updatedPayslip.getNetPay());

        // ✅ On enregistre la fiche mise à jour
        return payslipService.save(existingPayslip);
    }

    @DeleteMapping("/{id}")
    public void deletePayslip(@PathVariable Long id) {
        payslipService.deleteById(id);
    }
}
