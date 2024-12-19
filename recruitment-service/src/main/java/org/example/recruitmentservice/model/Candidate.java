package org.example.recruitmentservice.model;

import lombok.*;
import jakarta.persistence.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String positionApplied; // changé de "position" à "positionApplied"

    @Column(nullable = false)
    private String status; // Possible values: Applied, Interview, Rejected, Hired
}
