package org.example.notificationservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipient;

    private String message;

    private LocalDateTime timestamp;

    private String type; // Ajout de l'attribut type (INFO, WARNING, ERROR)

    @Column(nullable = false) // Empêche les valeurs NULL
    private boolean read = false; // Définit la valeur par défaut à false
}
