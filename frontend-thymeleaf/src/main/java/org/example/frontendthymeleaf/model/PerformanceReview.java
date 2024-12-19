package org.example.frontendthymeleaf.model;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceReview {
    private Long id;
    private Long employeeId;
    private LocalDate reviewDate;
    private String feedback;
    private int rating;
}

