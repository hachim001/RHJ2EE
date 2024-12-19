package org.example.performanceservice.controller;

import org.example.performanceservice.model.PerformanceReview;
import org.example.performanceservice.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performance")
public class PerformanceController {
    @Autowired
    private PerformanceService performanceService;

    @PostMapping
    public PerformanceReview addReview(@RequestBody PerformanceReview review) {
        return performanceService.addPerformanceReview(review);
    }

    @GetMapping("/employee/{employeeId}")
    public List<PerformanceReview> getReviewsByEmployee(@PathVariable Long employeeId) {
        return performanceService.getReviewsByEmployee(employeeId);
    }
    @GetMapping
    public List<PerformanceReview> getAllPerformances() {
        return performanceService.getAllPerformances();
    }


}

