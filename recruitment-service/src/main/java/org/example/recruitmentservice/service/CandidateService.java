package org.example.recruitmentservice.service;

import org.example.recruitmentservice.model.Candidate;
import org.example.recruitmentservice.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CandidateService {
    @Autowired
    private CandidateRepository repository;

    public Candidate addCandidate(Candidate candidate) {
        candidate.setStatus("Applied"); // Définit le statut initial
        return repository.save(candidate);
    }

    public Candidate updateCandidateStatus(Long id, String status) {
        Candidate candidate = repository.findById(id).orElseThrow(() -> new RuntimeException("Candidate not found"));
        candidate.setStatus(status);
        return repository.save(candidate);
    }

    public List<Candidate> getAllCandidates() {
        return repository.findAll();
    }

    public Candidate getCandidateById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Candidate not found"));
    }

    public void deleteCandidate(Long id) {
        repository.deleteById(id);
    }
}
