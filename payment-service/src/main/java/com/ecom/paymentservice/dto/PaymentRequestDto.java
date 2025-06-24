package com.ecom.paymentservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {
    Long amount;
    String name;
    String orderId;
    String phoneNumber;
}
