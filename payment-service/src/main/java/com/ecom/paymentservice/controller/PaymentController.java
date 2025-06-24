package com.ecom.paymentservice.controller;

import com.ecom.paymentservice.dto.PaymentRequestDto;
import com.ecom.paymentservice.service.PaymentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private PaymentService paymentService;
    public String intiatePayment(@RequestBody PaymentRequestDto paymentRequestDto){
        return "";
    }
}
