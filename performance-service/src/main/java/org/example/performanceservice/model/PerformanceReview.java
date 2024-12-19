package org.example.performanceservice.model;

import lombok.*;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reviewDate;

    private String feedback;
    private int rating;
}
