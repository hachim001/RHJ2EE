package org.example.frontendthymeleaf.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private String department;
    private double salary;
    private boolean active;
}
