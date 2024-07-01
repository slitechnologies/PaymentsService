package co.zw.telone.insurerpaymentservice.service;


import co.zw.telone.insurerpaymentservice.dto.CreatePaymentRequest;
import co.zw.telone.insurerpaymentservice.dto.CreatePaymentResponse;
import co.zw.telone.insurerpaymentservice.dto.TotalPaymentResponse;
import co.zw.telone.insurerpaymentservice.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    CreatePaymentResponse createPayment(CreatePaymentRequest request);

    CreatePaymentResponse getPayment(Long paymentId) throws NotFoundException;

    //    CreatePaymentResponse updatePayment(Long paymentId, CreatePaymentRequest request);
    CreatePaymentResponse getPaymentByReference(String reference) throws NotFoundException;

    void deletePayment(Long paymentId);

    List<CreatePaymentResponse> getAllPayments();

    List<CreatePaymentResponse> getPaymentsByInsurerId(Long insurerId);

    //    List<CreatePaymentResponse> getAllPaymentsByDate(LocalDate dateOfCreation);
    List<TotalPaymentResponse> getDailyTotalPayment(LocalDate dateCreated);

    List<TotalPaymentResponse> getPaymentByDateRange(LocalDate startDate, LocalDate endDate);
    TotalPaymentResponse getTotalPaymentsInDateRange(LocalDate startDate, LocalDate endDate);
}
