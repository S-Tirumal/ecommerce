package com.ecom.paymentservice.service;

import com.ecom.paymentservice.paymentgateway.PaymentGateway;
import com.ecom.paymentservice.paymentgateway.PaymentGatewayStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentGatewayStrategy paymentGatewayStrategy;

    @Autowired
    public PaymentServiceImpl(PaymentGatewayStrategy paymentGatewayStrategy) {
        this.paymentGatewayStrategy = paymentGatewayStrategy;
    }

    @Override
    public String getPaymentLink(Long amount,String orderId,String phoneNumber,String name) {
        PaymentGateway paymentGateway = paymentGatewayStrategy.getPaymentGateway(amount, orderId, phoneNumber, name);
        return paymentGateway.getPaymentLink(amount, orderId, phoneNumber, name);
    }
}
