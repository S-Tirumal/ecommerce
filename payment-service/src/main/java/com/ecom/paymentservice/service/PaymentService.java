package com.ecom.paymentservice.service;

public interface PaymentService {
    public String getPaymentLink(Long amount,String orderId,String phoneNumber,String name);
}
