package org.example.frontendthymeleaf.controller;

import org.example.frontendthymeleaf.model.PerformanceReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/performance")
public class PerformanceController {

    @Autowired
    private RestTemplate restTemplate;

    // Afficher la liste des évaluations de performance
    @GetMapping
    public String getPerformanceReviews(Model model) {
        PerformanceReview[] reviews = restTemplate.getForObject("http://performance-service/performance", PerformanceReview[].class);
        model.addAttribute("reviews", reviews);
        model.addAttribute("activePage", "performance");
        return "performance";
    }

    // Afficher le formulaire d'ajout d'une évaluation de performance
    @GetMapping("/add")
    public String showAddPerformanceForm(Model model) {
        model.addAttribute("performanceReview", new PerformanceReview());
        return "add_performance";
    }

    // Traiter l'ajout d'une évaluation de performance
    @PostMapping
    public String addPerformanceReview(@ModelAttribute PerformanceReview performanceReview) {
        restTemplate.postForObject("http://performance-service/performance", performanceReview, PerformanceReview.class);
        return "redirect:/performance";
    }

    // Afficher le formulaire de modification d'une évaluation de performance
    @GetMapping("/edit/{id}")
    public String showEditPerformanceForm(@PathVariable Long id, Model model) {
        PerformanceReview review = restTemplate.getForObject("http://performance-service/performance/" + id, PerformanceReview.class);
        model.addAttribute("performanceReview", review);
        return "edit_performance";
    }

    // Traiter la modification d'une évaluation de performance
    @PostMapping("/update/{id}")
    public String updatePerformanceReview(@PathVariable Long id, @ModelAttribute PerformanceReview performanceReview) {
        restTemplate.put("http://performance-service/performance/" + id, performanceReview);
        return "redirect:/performance";
    }

    // Supprimer une évaluation de performance
    @GetMapping("/delete/{id}")
    public String deletePerformanceReview(@PathVariable Long id) {
        restTemplate.delete("http://performance-service/performance/" + id);
        return "redirect:/performance";
    }
}
