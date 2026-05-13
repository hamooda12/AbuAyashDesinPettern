package com.example.hotalproject.HotelCatalog.notification;

import com.example.hotalproject.HotelCatalog.payment.PaymentFailedEvent;
import com.example.hotalproject.HotelCatalog.payment.PaymentInitiatedEvent;
import com.example.hotalproject.HotelCatalog.payment.PaymentRefundedEvent;
import com.example.hotalproject.HotelCatalog.payment.PaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentNotificationListener {

    private final NotificationSenderFactory notificationSenderFactory;

    @EventListener
    public void handlePaymentSuccess(PaymentSuccessEvent event) {
        notificationSenderFactory.getSender(NotificationChannel.SMS).send(
                event.getGuestEmail(),
                NotificationType.PAYMENT_SUCCESS,
                "Payment successful",
                "Payment for booking #" + event.getBookingId() + " was successful."
        );
    }

    @EventListener
    public void handlePaymentFailed(PaymentFailedEvent event) {
        notificationSenderFactory.getSender(NotificationChannel.SMS).send(
                event.getGuestEmail(),
                NotificationType.PAYMENT_FAILED,
                "Payment failed",
                "Payment for booking #" + event.getBookingId() + " has failed."
        );
    }
    @EventListener
    public void handlePaymentInitiated(PaymentInitiatedEvent event) {
        notificationSenderFactory.getSender(NotificationChannel.SMS).send(
                event.getGuestEmail(),
                NotificationType.PAYMENT_INITIATED,
                "Payment initiated",
                "Payment intent created for booking #" + event.getBookingId()
                        + ". Reference: " + event.getProviderRef()
        );
    }

    @EventListener
    public void handlePaymentRefunded(PaymentRefundedEvent event) {
        notificationSenderFactory.getSender(NotificationChannel.SMS).send(
                event.getGuestEmail(),
                NotificationType.PAYMENT_REFUNDED,
                "Payment refunded",
                "Payment for booking #" + event.getBookingId() + " has been refunded."
        );
    }
}