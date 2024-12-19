package org.example.leaveservice.service;



import org.example.leaveservice.model.LeaveRequest;
import org.example.leaveservice.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    public List<LeaveRequest> getAllLeaves() {
        return leaveRepository.findAll();
    }

    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) {
        return leaveRepository.save(leaveRequest);
    }

    public LeaveRequest approveLeave(Long id) {
        LeaveRequest leave = leaveRepository.findById(id).orElseThrow(() -> new RuntimeException("Leave request not found"));
        leave.setStatus("Approved");
        return leaveRepository.save(leave);
    }

    public LeaveRequest rejectLeave(Long id) {
        LeaveRequest leave = leaveRepository.findById(id).orElseThrow(() -> new RuntimeException("Leave request not found"));
        leave.setStatus("Rejected");
        return leaveRepository.save(leave);
    }
    public LeaveRequest getLeaveById(Long id) {
        return leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
    }
    public LeaveRequest updateLeave(Long id, LeaveRequest updatedLeave) {
        LeaveRequest existing = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        existing.setEmployeeId(updatedLeave.getEmployeeId());
        existing.setStartDate(updatedLeave.getStartDate());
        existing.setEndDate(updatedLeave.getEndDate());
        existing.setReason(updatedLeave.getReason());
        existing.setStatus(updatedLeave.getStatus());

        return leaveRepository.save(existing);
    }
    public void deleteLeave(Long id) {
        LeaveRequest leave = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
        leaveRepository.delete(leave);
    }



}

