package co.zw.telone.insurerpaymentservice.service.impl;

import co.zw.telone.insurerpaymentservice.constants.Currency;
import co.zw.telone.insurerpaymentservice.dto.*;
import co.zw.telone.insurerpaymentservice.exceptions.NotFoundException;
import co.zw.telone.insurerpaymentservice.exceptions.ResourceNotFoundException;
import co.zw.telone.insurerpaymentservice.model.Payment;
import co.zw.telone.insurerpaymentservice.repository.PaymentRepository;
import co.zw.telone.insurerpaymentservice.service.PaymentService;
import co.zw.telone.insurerpaymentservice.utils.PaymentUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private static final String NOT_FOUND = "Not Found!";

    @Override
    public CreatePaymentResponse createPayment(CreatePaymentRequest request) {

        var payment = Payment.builder()
                .referenceNumber(PaymentUtils.generateReferenceNumber())
                .insurerId(request.getInsurerId())
                .productId(request.getProductId())
                .clientId(request.getClientId())
                .userId(request.getUserId())
                .salesAgentId(request.getSalesAgentId())
                .propertyId(request.getPropertyId())
                .amount(request.getAmount())
                .currencyCode(request.getCurrencyCode())
                .method(request.getMethod())
                .startDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(4))
                .status(request.getStatus())
                .build();

        Payment savedPayment = paymentRepository.save(payment);


        return CreatePaymentResponse.builder()
                .referenceNumber(savedPayment.getReferenceNumber())
                .paymentId(savedPayment.getId())
                .insurerId(savedPayment.getInsurerId())
                .productId(savedPayment.getProductId())
                .clientId(savedPayment.getClientId())
                .userId(savedPayment.getUserId())
                .createdAt(savedPayment.getCreatedAt())
                .salesAgentId(savedPayment.getSalesAgentId())
                .propertyId(savedPayment.getPropertyId())
                .amount(savedPayment.getAmount())
                .currency(savedPayment.getCurrencyCode())
                .method(savedPayment.getMethod())
                .startDate(savedPayment.getStartDate())
                .endDate(savedPayment.getEndDate())
                .status(savedPayment.getStatus())
                .build();
    }


    @Override
    public CreatePaymentResponse getPayment(Long paymentId) {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);

        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();

            return CreatePaymentResponse.builder()
                    .referenceNumber(payment.getReferenceNumber())
                    .paymentId(payment.getId())
                    .insurerId(payment.getInsurerId())
                    .productId(payment.getProductId())
                    .clientId(payment.getClientId())
                    .userId(payment.getUserId())
                    .currency(payment.getCurrencyCode())
                    .createdAt(payment.getCreatedAt())
                    .salesAgentId(payment.getSalesAgentId())
                    .propertyId(payment.getPropertyId())
                    .amount(payment.getAmount())
                    .method(payment.getMethod())
                    .startDate(payment.getStartDate())
                    .endDate(payment.getEndDate())
                    .status(payment.getStatus())
                    .build();
        } else {
            throw new ResourceNotFoundException("Not Found");
        }
    }

    @Override
    public CreatePaymentResponse getPaymentByReference(String reference) throws NotFoundException {
        Payment payment = paymentRepository.findByReferenceNumber(reference);
        return CreatePaymentResponse.builder()
                .referenceNumber(payment.getReferenceNumber())
                .paymentId(payment.getId())
                .insurerId(payment.getInsurerId())
                .productId(payment.getProductId())
                .userId(payment.getUserId())
                .clientId(payment.getClientId())
                .createdAt(payment.getCreatedAt())
                .salesAgentId(payment.getSalesAgentId())
                .propertyId(payment.getPropertyId())
                .amount(payment.getAmount())
                .currency(payment.getCurrencyCode())
                .method(payment.getMethod())
                .startDate(payment.getStartDate())
                .endDate(payment.getEndDate())
                .status(payment.getStatus())
                .build();
    }

    @Override
    public void deletePayment(Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    @Override
    public List<CreatePaymentResponse> getAllPayments() {
        List<Payment> payment = paymentRepository.findAll();
        if (payment.isEmpty())
            throw new ResourceNotFoundException(NOT_FOUND);
        return payment.stream().map(this::mapToPaymentResponse).toList();
    }



    @Override
    public List<SumOfPaymentsResponse> getPaymentsByInsurerId(Long insurerId) {
        List<Payment> payments = paymentRepository.findByInsurerId(insurerId);
        if (payments.isEmpty())
            throw new ResourceNotFoundException(NOT_FOUND);

        return getSumOfPaymentsResponses(payments);
    }

    private List<SumOfPaymentsResponse> getSumOfPaymentsResponses(List<Payment> payments) {
        Map<Currency, BigDecimal> totalAmountPerCurrency = payments.stream()
                .collect(Collectors.groupingBy(
                        Payment::getCurrencyCode,
                        Collectors.reducing(BigDecimal.ZERO, Payment::getAmount, BigDecimal::add)
                ));

        List<SumOfPaymentsResponse> responses = new ArrayList<>();
        for (Map.Entry<Currency, BigDecimal> entry : totalAmountPerCurrency.entrySet()) {
            responses.add(
                    SumOfPaymentsResponse.builder()
                            .policyName("Third Party")
                            .categoryName("Motor Vehicle Insurance")
                            .totalAmount(entry.getValue())
                            .currencyCode(entry.getKey())
                            .build()
            );
        }

        return responses;
    }


    @Override
    public List<SumOfPaymentsResponse> getSumOfPaymentsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Payment> payments = paymentRepository.findByCreatedAtBetween(startDate, endDate);
        return getSumOfPaymentsResponses(payments);
    }


    @Override
    public List<SumOfPaymentsResponse> getSumOfPaymentsByDate(LocalDate dateOfTransaction) {
        List<Payment> payments = paymentRepository.findByCreatedAt(dateOfTransaction);
        return getSumOfPaymentsResponses(payments);
    }

    @Override
    public List<TaxReportResponse> getTaxReportByDate(LocalDate dateOfTransaction) {
        List<Payment> payments = paymentRepository.findByCreatedAt(dateOfTransaction);
        return getTaxReportResponses(payments);
    }

    private List<TaxReportResponse> getTaxReportResponses(List<Payment> payments) {
        if (payments.isEmpty())
            throw new ResourceNotFoundException(NOT_FOUND);

        Map<Currency, BigDecimal> totalAmountPerCurrency = payments.stream()
                .collect(Collectors.groupingBy(
                        Payment::getCurrencyCode,
                        Collectors.reducing(BigDecimal.ZERO, Payment::getAmount, BigDecimal::add)
                ));

        List<TaxReportResponse> responses = new ArrayList<>();
        for (Map.Entry<Currency, BigDecimal> entry : totalAmountPerCurrency.entrySet()) {
            BigDecimal taxAmount = entry.getValue().multiply(new BigDecimal("0.02")); // 2% tax
            responses.add(
                    TaxReportResponse.builder()
                            .policyName("Third Party")
                            .categoryName("Motor Vehicle Insurance")
                            .totalAmount(entry.getValue())
                            .taxAmount(taxAmount)
                            .currencyCode(entry.getKey())
                            .build()
            );
        }

        return responses;
    }

    @Override
    public List<TaxReportResponse> getTaxReportTotal() {
        List<Payment> payments = paymentRepository.findAll();
        return getTaxReportResponses(payments);
    }

    @Override
    public List<CommissionReportResponse> getCommissionReportTotal() {
        List<Payment> payments = paymentRepository.findAll();
        if (payments.isEmpty())
            return Collections.emptyList();

        Map<Currency, BigDecimal> totalAmountPerCurrency = payments.stream()
                .collect(Collectors.groupingBy(
                        Payment::getCurrencyCode,
                        Collectors.reducing(BigDecimal.ZERO, Payment::getAmount, BigDecimal::add)
                ));

        List<CommissionReportResponse> responses = new ArrayList<>();
        for (Map.Entry<Currency, BigDecimal> entry : totalAmountPerCurrency.entrySet()) {
            BigDecimal commissionAmount = entry.getValue().multiply(new BigDecimal("0.10")); // 10% commission
            CommissionReportResponse response = CommissionReportResponse.builder()
                    .policyName("Third Party")
                    .categoryName("Motor Vehicle Insurance")
                    .totalAmount(entry.getValue())
                    .commissionAmount(commissionAmount)
                    .currencyCode(entry.getKey())
                    .build();
            responses.add(response);
        }

        return responses;
    }

    @Override
    public List<CommissionReportResponse> getCommissionReportTotalByInsurerId(Long insurerId) {
        List<Payment> payments = paymentRepository.findByInsurerId(insurerId);
        if (payments.isEmpty())
            throw new ResourceNotFoundException(NOT_FOUND);

        return getCommissionReportResponses(payments);
    }

    private List<CommissionReportResponse> getCommissionReportResponses(List<Payment> payments) {
        Map<Currency, BigDecimal> totalAmountPerCurrency = payments.stream()
                .collect(Collectors.groupingBy(
                        Payment::getCurrencyCode,
                        Collectors.reducing(BigDecimal.ZERO, Payment::getAmount, BigDecimal::add)
                ));

        List<CommissionReportResponse> responses = new ArrayList<>();
        for (Map.Entry<Currency, BigDecimal> entry : totalAmountPerCurrency.entrySet()) {
            BigDecimal commissionAmount = entry.getValue().multiply(new BigDecimal("0.10")); // 10% commission
            responses.add(
                    CommissionReportResponse.builder()
                            .policyName("Third Party")
                            .categoryName("Motor Vehicle Insurance")
                            .totalAmount(entry.getValue())
                            .commissionAmount(commissionAmount)
                            .currencyCode(entry.getKey())
                            .build()
            );
        }

        return responses;
    }

    @Override
    public List<CommissionReportResponse> getCommissionReportTotalByDate(LocalDate dateOfTransaction) {
        List<Payment> payments = paymentRepository.findByCreatedAt(dateOfTransaction);
        if (payments.isEmpty())
            throw new ResourceNotFoundException(NOT_FOUND);

        return getCommissionReportResponses(payments);
    }

    @Override
    public List<CommissionReportResponse> getCommissionReportTotalByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Payment> payments = paymentRepository.findByCreatedAtBetween(startDate, endDate);
        if (payments.isEmpty())
            throw new ResourceNotFoundException(NOT_FOUND);

        return getCommissionReportResponses(payments);
    }


    private CreatePaymentResponse mapToPaymentResponse(Payment payment) {
        return CreatePaymentResponse.builder()
                .referenceNumber(payment.getReferenceNumber())
                .paymentId(payment.getId())
                .insurerId(payment.getInsurerId())
                .propertyId(payment.getPropertyId())
                .userId(payment.getUserId())
                .clientId(payment.getClientId())
                .createdAt(payment.getCreatedAt())
                .salesAgentId(payment.getSalesAgentId())
                .propertyId(payment.getPropertyId())
                .productId(payment.getProductId())
                .userId(payment.getUserId())
                .amount(payment.getAmount())
                .currency(payment.getCurrencyCode())
                .currency(payment.getCurrencyCode())
                .method(payment.getMethod())
                .startDate(payment.getStartDate())
                .endDate(payment.getEndDate())
                .status(payment.getStatus())
                .build();
    }

}