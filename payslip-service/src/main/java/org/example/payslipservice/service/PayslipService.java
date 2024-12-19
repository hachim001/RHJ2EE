package org.example.payslipservice.service;

import org.example.payslipservice.model.Payslip;
import org.example.payslipservice.repository.PayslipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PayslipService {
    @Autowired
    private PayslipRepository repository;

    // Génération d'une nouvelle fiche de paie
    public Payslip generatePayslip(Payslip payslip) {
        payslip.setNetPay(payslip.getBasicSalary() + payslip.getBonuses() - payslip.getDeductions());
        return repository.save(payslip);
    }

    // Récupérer la liste des fiches de paie d'un employé
    public List<Payslip> getPayslipsByEmployee(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    // Récupérer la liste de toutes les fiches de paie
    public List<Payslip> getAllPayslips() {
        return repository.findAll();
    }

    // 🚀 Ajout de la méthode findById() pour récupérer une fiche de paie par ID
    public Optional<Payslip> findById(Long id) {
        return repository.findById(id);
    }

    // 🚀 Ajout de la méthode save() pour enregistrer une fiche de paie modifiée
    public Payslip save(Payslip payslip) {
        return repository.save(payslip);
    }

    // 🚀 Ajout de la méthode deleteById() pour supprimer une fiche de paie par ID
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
