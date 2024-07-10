package co.zw.telone.insurerpaymentservice.service;


import co.zw.telone.insurerpaymentservice.dto.*;
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

    List<SumOfPaymentsResponse> getPaymentsByInsurerId(Long insurerId);

    List<SumOfPaymentsResponse> getSumOfPaymentsByDateRange(LocalDate startDate, LocalDate endDate);

    List<SumOfPaymentsResponse> getSumOfPaymentsByDate(LocalDate dateOfTransaction);

    List<TaxReportResponse> getTaxReportByDate(LocalDate dateOfTransaction);
    List<TaxReportResponse> getTaxReportTotal();
}
