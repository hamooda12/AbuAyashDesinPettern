package com.example.hotalproject.HotelCatalog.payment;

import com.example.hotalproject.HotelCatalog.booking.Booking;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CardPaymentStrategy implements PaymentStrategy {

    @Override
    public Payment pay(Booking booking) {
        return Payment.builder()
                .booking(booking)
                .amount(booking.getTotalPrice())
                .status(PaymentStatus.INITIATED)
                .providerRef("CARD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                .build();
    }
}