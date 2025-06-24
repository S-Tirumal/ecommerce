package com.ecom.paymentservice.paymentgateway;

public class RazorpayPaymentGateway implements PaymentGateway{
    @Override
    public String getPaymentLink(Long amount, String orderId, String phoneNumber, String name) {
        return "";
    }
}
