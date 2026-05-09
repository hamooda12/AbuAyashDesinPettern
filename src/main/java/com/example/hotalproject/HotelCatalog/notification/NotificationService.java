package com.example.hotalproject.HotelCatalog.notification;

import java.util.List;

public interface NotificationService {

    NotificationResponse send(String recipient,
                              NotificationType type,
                              NotificationChannel channel,
                              String subject,
                              String message);

    List<NotificationResponse> getByRecipient(String recipient);
}