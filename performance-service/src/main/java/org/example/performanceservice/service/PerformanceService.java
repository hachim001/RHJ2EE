package org.example.performanceservice.service;

import org.example.performanceservice.model.PerformanceReview;
import org.example.performanceservice.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceService {
    @Autowired
    private PerformanceRepository repository;

    public PerformanceReview addPerformanceReview(PerformanceReview review) {
        return repository.save(review);
    }

    public List<PerformanceReview> getReviewsByEmployee(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }
    public List<PerformanceReview> getAllPerformances() {
        return repository.findAll(); // Méthode standard de JPA Repository.
    }
}

