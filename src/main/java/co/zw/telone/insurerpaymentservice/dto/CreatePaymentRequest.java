package co.zw.telone.insurerpaymentservice.dto;

import co.zw.telone.insurerpaymentservice.constants.PaymentMethod;
import co.zw.telone.insurerpaymentservice.constants.PaymentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {
    private String referenceNumber;
    private Long insurerId;
    private Long productId;
    private LocalDate createdAt;
    private Long clientId;
    private Long salesAgentId;
    private Long propertyId;
    private BigDecimal usdAmount;
    private BigDecimal zigAmount;
    private BigDecimal totalUsdAmount;
    private BigDecimal totalZigAmount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
