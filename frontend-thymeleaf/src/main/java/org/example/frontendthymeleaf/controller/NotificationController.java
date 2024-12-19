package org.example.frontendthymeleaf.controller;

import org.example.frontendthymeleaf.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private RestTemplate restTemplate;

    // Afficher la liste des notifications
    @GetMapping
    public String getNotifications(Model model) {
        Notification[] notifications = restTemplate.getForObject("http://notification-service/notifications", Notification[].class);
        model.addAttribute("notifications", notifications);
        model.addAttribute("activePage", "notifications");
        return "notifications";
    }

    // Afficher le formulaire d'envoi d'une notification
    @GetMapping("/add")
    public String showAddNotificationForm(Model model) {
        model.addAttribute("notification", new Notification());
        return "add_notification";
    }

    // Traiter l'envoi d'une notification
    @PostMapping
    public String addNotification(@ModelAttribute Notification notification) {
        restTemplate.postForObject("http://notification-service/notifications", notification, Notification.class);
        return "redirect:/notifications";
    }

    // Afficher le formulaire de modification d'une notification
    @GetMapping("/edit/{id}")
    public String showEditNotificationForm(@PathVariable Long id, Model model) {
        Notification notification = restTemplate.getForObject("http://notification-service/notifications/" + id, Notification.class);
        model.addAttribute("notification", notification);
        return "edit_notification";
    }

    // Traiter la modification d'une notification
    @PostMapping("/update/{id}")
    public String updateNotification(@PathVariable Long id, @ModelAttribute Notification notification) {
        restTemplate.put("http://notification-service/notifications/" + id, notification);
        return "redirect:/notifications";
    }

    // Supprimer une notification
    @GetMapping("/delete/{id}")
    public String deleteNotification(@PathVariable Long id) {
        restTemplate.delete("http://notification-service/notifications/" + id);
        return "redirect:/notifications";
    }
}
