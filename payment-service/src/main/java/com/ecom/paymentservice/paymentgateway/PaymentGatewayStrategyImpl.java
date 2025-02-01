package com.ecom.paymentservice.paymentgateway;

import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayStrategyImpl implements PaymentGatewayStrategy{

    @Override
    public PaymentGateway getPaymentGateway(Long amount, String orderId, String phoneNumber, String name) {
        return null;
    }
}
