package co.zw.telone.insurerpaymentservice.dto;

import co.zw.telone.insurerpaymentservice.constants.PaymentMethod;
import co.zw.telone.insurerpaymentservice.constants.PaymentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentResponse {
    private String referenceNumber;
    private  Long paymentId;
    private Long insurerId;
    private Long productId;
    private LocalDate createdAt;
    private Long clientId;
    private Long salesAgentId;
    private Long propertyId;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}