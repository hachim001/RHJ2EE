package org.example.notificationservice.service;

import org.example.notificationservice.model.Notification;
import org.example.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository repository;

    public Notification sendNotification(Notification notification) {
        notification.setTimestamp(LocalDateTime.now());
        return repository.save(notification);
    }

    public List<Notification> getAllNotifications() {
        return repository.findAll();
    }

    public Notification getNotificationById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    public Notification updateNotification(Long id, Notification newNotification) {
        Notification existingNotification = repository.findById(id).orElseThrow(() -> new RuntimeException("Notification not found"));
        existingNotification.setMessage(newNotification.getMessage());
        existingNotification.setRecipient(newNotification.getRecipient());
        existingNotification.setType(newNotification.getType());
        existingNotification.setRead(newNotification.isRead());
        return repository.save(existingNotification);
    }

    public void deleteNotification(Long id) {
        repository.deleteById(id);
    }
}
