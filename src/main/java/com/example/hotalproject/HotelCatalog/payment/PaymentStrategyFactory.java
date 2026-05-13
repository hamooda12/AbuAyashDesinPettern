package com.example.hotalproject.HotelCatalog.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyFactory {

    private final CardPaymentStrategy cardPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy getStrategy(PaymentMethod method) {
        return switch (method) {
            case CARD -> cardPaymentStrategy;
            case CASH -> cashPaymentStrategy;
            default -> throw new IllegalArgumentException("Unsupported payment method: " + method);
        };
    }
}