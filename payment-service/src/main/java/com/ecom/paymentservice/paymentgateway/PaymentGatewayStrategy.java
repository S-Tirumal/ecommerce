package com.ecom.paymentservice.paymentgateway;

public interface PaymentGatewayStrategy {
    PaymentGateway getPaymentGateway(Long amount, String orderId, String phoneNumber, String name);
}
