package org.example.frontendthymeleaf.controller;

import org.example.frontendthymeleaf.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/recruitments")
public class RecruitmentController {

    @Autowired
    private RestTemplate restTemplate;

    // Afficher la liste des candidats
    @GetMapping
    public String getCandidates(Model model) {
        Candidate[] candidates = restTemplate.getForObject("http://recruitment-service/candidates", Candidate[].class);
        model.addAttribute("candidates", candidates);
        model.addAttribute("activePage", "recruitments");
        return "recruitments";
    }

    // Traiter l'ajout d'un candidat
    @PostMapping
    public String addCandidate(@ModelAttribute Candidate candidate) {
        restTemplate.postForObject("http://recruitment-service/candidates", candidate, Candidate.class);
        return "redirect:/recruitments";
    }


    // Afficher le formulaire de modification d'un candidat
    @GetMapping("/edit/{id}")
    public String showEditCandidateForm(@PathVariable Long id, Model model) {
        Candidate candidate = restTemplate.getForObject("http://recruitment-service/candidates/" + id, Candidate.class);
        model.addAttribute("candidate", candidate);
        return "edit_candidate";
    }

    // Traiter la modification d'un candidat
    @PostMapping("/update/{id}")
    public String updateCandidate(@PathVariable Long id, @ModelAttribute Candidate candidate) {
        restTemplate.put("http://recruitment-service/candidates/" + id, candidate);
        return "redirect:/recruitments";
    }

    // Supprimer un candidat
    @GetMapping("/delete/{id}")
    public String deleteCandidate(@PathVariable Long id) {
        restTemplate.delete("http://recruitment-service/candidates/" + id);
        return "redirect:/recruitments";
    }
    @GetMapping("/recruitments/add")
    public String showAddCandidateForm(Model model) {
        model.addAttribute("candidate", new Candidate());
        return "add_candidate";
    }

}
