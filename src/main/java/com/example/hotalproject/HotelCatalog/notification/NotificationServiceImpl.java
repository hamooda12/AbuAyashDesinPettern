package com.example.hotalproject.HotelCatalog.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationSenderFactory notificationSenderFactory;

    @Override
    public NotificationResponse send(String recipient,
                                     NotificationType type,
                                     NotificationChannel channel,
                                     String subject,
                                     String message) {

        NotificationSender sender = notificationSenderFactory.getSender(channel);

        Notification notification = sender.send(
                recipient,
                type,
                subject,
                message
        );

        notification = notificationRepository.save(notification);

        return toResponse(notification);
    }

    @Override
    public List<NotificationResponse> getByRecipient(String recipient) {
        return notificationRepository.findByRecipientOrderByCreatedAtDesc(recipient)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private NotificationResponse toResponse(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .recipient(notification.getRecipient())
                .type(notification.getType())
                .channel(notification.getChannel())
                .subject(notification.getSubject())
                .message(notification.getMessage())
                .status(notification.getStatus())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}