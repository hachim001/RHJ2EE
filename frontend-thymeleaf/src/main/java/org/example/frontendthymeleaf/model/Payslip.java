package org.example.frontendthymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payslip {
    private Long id;
    private Long employeeId;
    private LocalDate dateIssued;
    private double basicSalary;
    private double grossSalary;
    private double bonuses;
    private double deductions;
    private double netPay;
}
