package com.example.hotalproject.HotelCatalog.notification;

public interface NotificationSender {

    Notification send(String recipient,
                      NotificationType type,
                      String subject,
                      String message);
}