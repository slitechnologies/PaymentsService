package co.zw.telone.insurerpaymentservice.service;


import co.zw.telone.insurerpaymentservice.dto.CreatePaymentRequest;
import co.zw.telone.insurerpaymentservice.dto.CreatePaymentResponse;
import co.zw.telone.insurerpaymentservice.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {
    CreatePaymentResponse createPayment(CreatePaymentRequest request);
    CreatePaymentResponse getPayment(Long paymentId) throws NotFoundException;
//    CreatePaymentResponse updatePayment(Long paymentId, CreatePaymentRequest request);
    CreatePaymentResponse getPaymentByReference(String reference) throws NotFoundException;
    void deletePayment(Long paymentId);
    List<CreatePaymentResponse> getAllPayments();
    List<CreatePaymentResponse> getPaymentsByInsurerId(Long insurerId);
    List<CreatePaymentResponse> getAllPaymentsByDate(LocalDateTime dateOfCreation);
}
