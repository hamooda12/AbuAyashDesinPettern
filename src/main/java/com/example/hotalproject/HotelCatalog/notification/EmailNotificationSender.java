package com.example.hotalproject.HotelCatalog.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailNotificationSender implements NotificationSender {

    @Override
    public Notification send(String recipient,
                             NotificationType type,
                             String subject,
                             String message) {

        log.info("Sending EMAIL notification to {} | type={} | subject={}",
                recipient, type, subject);

        return Notification.builder()
                .recipient(recipient)
                .type(type)
                .channel(NotificationChannel.EMAIL)
                .subject(subject)
                .message(message)
                .status(NotificationStatus.SENT)
                .build();
    }
}