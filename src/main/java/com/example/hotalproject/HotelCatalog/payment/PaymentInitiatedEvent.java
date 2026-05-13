package com.example.hotalproject.HotelCatalog.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentInitiatedEvent {

    private final Long bookingId;
    private final String guestEmail;
    private final String providerRef;
}