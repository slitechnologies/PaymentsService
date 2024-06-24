package co.zw.telone.insurerpaymentservice.service.impl;

import co.zw.telone.insurerpaymentservice.dto.CreatePaymentRequest;
import co.zw.telone.insurerpaymentservice.dto.CreatePaymentResponse;
import co.zw.telone.insurerpaymentservice.exceptions.NotFoundException;
import co.zw.telone.insurerpaymentservice.exceptions.ResourceNotFoundException;
import co.zw.telone.insurerpaymentservice.model.Payment;
import co.zw.telone.insurerpaymentservice.repository.PaymentRepository;
import co.zw.telone.insurerpaymentservice.service.PaymentService;
import co.zw.telone.insurerpaymentservice.utils.PaymentUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl  implements PaymentService {

    private final PaymentRepository paymentRepository;
    private  static final String NOT_FOUND = "Not Found!";

    @Override
    public CreatePaymentResponse createPayment(CreatePaymentRequest request) {

        var payment = Payment.builder()
                .referenceNumber(PaymentUtils.generateReferenceNumber())
                .insurerId(request.getInsurerId())
                .productId(request.getProductId())
                .clientId(request.getClientId())
                .salesAgentId(request.getSalesAgentId())
                .propertyId(request.getPropertyId())
                .amount(request.getAmount())
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
                    .createdAt(savedPayment.getCreatedAt())
                    .salesAgentId(savedPayment.getSalesAgentId())
                    .propertyId(savedPayment.getPropertyId())
                    .amount(savedPayment.getAmount())
                    .method(savedPayment.getMethod())
                    .startDate(savedPayment.getStartDate())
                    .endDate(savedPayment.getEndDate())
                    .status(savedPayment.getStatus())
                    .build();
    }


    @Override
    public CreatePaymentResponse getPayment(Long paymentId) throws NotFoundException {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);

        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();

           return CreatePaymentResponse.builder()
                   .referenceNumber(payment.getReferenceNumber())
                   .paymentId( payment.getId())
                   .insurerId(payment.getInsurerId())
                   .propertyId(payment.getProductId())
                   .createdAt(payment.getCreatedAt())
                   .salesAgentId(payment.getSalesAgentId())
                   .propertyId(payment.getPropertyId())
                   .amount(payment.getAmount())
                   .method(payment.getMethod())
                   .startDate(payment.getStartDate())
                   .endDate(payment.getEndDate())
                   .status( payment.getStatus())
                   .build();
        } else {
            throw new NotFoundException("Not Found");
        }
    }

    @Override
    public CreatePaymentResponse getPaymentByReference(String reference) throws NotFoundException {
        Payment payment = paymentRepository.findByReferenceNumber(reference);
        return CreatePaymentResponse.builder()
                .referenceNumber(payment.getReferenceNumber())
                .paymentId(payment.getId())
                .insurerId(payment.getInsurerId())
                .propertyId(payment.getProductId())
                .createdAt(payment.getCreatedAt())
                .salesAgentId(payment.getSalesAgentId())
                .propertyId(payment.getPropertyId())
                .amount(payment.getAmount())
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
    public List<CreatePaymentResponse> getPaymentsByInsurerId(Long insurerId) {
        List<Payment> payment = paymentRepository.findByInsurerId(insurerId);
        if (payment.isEmpty())
            throw new ResourceNotFoundException(NOT_FOUND);
        return payment.stream().map(this::mapToPaymentResponse).toList();
    }

    @Override
    public List<CreatePaymentResponse> getAllPaymentsByDate(LocalDateTime dateCreated) {
        List<Payment> payment = paymentRepository.findByCreatedAt(dateCreated);
        return payment.stream().map(this::mapToPaymentResponse).toList();
    }

    private CreatePaymentResponse mapToPaymentResponse(Payment payment) {
        return CreatePaymentResponse.builder()
                .referenceNumber(payment.getReferenceNumber())
                .paymentId(payment.getId())
                .insurerId(payment.getInsurerId())
                .propertyId(payment.getProductId())
                .createdAt(payment.getCreatedAt())
                .salesAgentId(payment.getSalesAgentId())
                .propertyId(payment.getPropertyId())
                .amount(payment.getAmount())
                .method(payment.getMethod())
                .startDate(payment.getStartDate())
                .endDate(payment.getEndDate())
                .status(payment.getStatus())
                .build();
    }

}