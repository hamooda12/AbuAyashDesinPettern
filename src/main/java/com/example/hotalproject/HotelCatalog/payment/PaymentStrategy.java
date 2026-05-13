package com.example.hotalproject.HotelCatalog.payment;

import com.example.hotalproject.HotelCatalog.booking.Booking;

public interface PaymentStrategy {

    Payment pay(Booking booking);
}