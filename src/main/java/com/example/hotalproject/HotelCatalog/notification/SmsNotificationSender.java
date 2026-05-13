package com.example.hotalproject.HotelCatalog.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SmsNotificationSender implements NotificationSender {

    @Override
    public Notification send(String recipient,
                             NotificationType type,
                             String subject,
                             String message) {

        log.info("Sending SMS notification to {} | type={}",
                recipient, type);

        return Notification.builder()
                .recipient(recipient)
                .type(type)
                .channel(NotificationChannel.SMS)
                .subject(subject)
                .message(message)
                .status(NotificationStatus.SENT)
                .build();
    }
}