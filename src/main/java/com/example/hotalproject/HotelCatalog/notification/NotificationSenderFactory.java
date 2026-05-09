package com.example.hotalproject.HotelCatalog.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationSenderFactory {

    private final EmailNotificationSender emailNotificationSender;
    private final SmsNotificationSender smsNotificationSender;
    private final InAppNotificationSender inAppNotificationSender;

    public NotificationSender getSender(NotificationChannel channel) {
        return switch (channel) {
            case EMAIL -> emailNotificationSender;
            case SMS -> smsNotificationSender;
            case IN_APP -> inAppNotificationSender;
        };
    }
}