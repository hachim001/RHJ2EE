package org.example.payslipservice.model;



import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payslip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private LocalDate dateIssued;
    private double basicSalary;
    private double grossSalary;
    private double bonuses;
    private double deductions;
    private double netPay;
}

