package org.example.frontendthymeleaf.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private Long id;
    private Long employeeId;
    private String message;
    private String type; // Par exemple : INFO, WARNING, ERROR
    private boolean read;
}

