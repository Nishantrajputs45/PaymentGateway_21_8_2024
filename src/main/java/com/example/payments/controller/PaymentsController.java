package com.example.payments.controller;

import com.example.payments.dtos.PaymentRequestDto;
import com.example.payments.service.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentsController {

    PaymentService paymentService;
    public PaymentsController(PaymentService paymentService){
        this.paymentService=paymentService;
    }

    @PostMapping("/payments")
    public ResponseEntity<String> createPaymentLink(@RequestBody PaymentRequestDto paymentRequestDto) throws StripeException {
        String paymentLink= paymentService.makePayment(paymentRequestDto.getOrderId(),paymentRequestDto.getPaymentAmount());

       return new ResponseEntity<>(paymentLink, HttpStatus.OK);

    }

    @PostMapping("/webhook")
    public String handleWebhook(){
        System.out.println("webhook request is receieved ");
        return "";
    }

}
