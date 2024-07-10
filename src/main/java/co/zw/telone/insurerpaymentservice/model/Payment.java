package co.zw.telone.insurerpaymentservice.model;


import co.zw.telone.insurerpaymentservice.constants.Currency;
import co.zw.telone.insurerpaymentservice.constants.PaymentMethod;
import co.zw.telone.insurerpaymentservice.constants.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String referenceNumber;
    private Long userId;
    private Long insurerId;
    private Long productId;
    private String policyName;
    private LocalDate createdAt;
    private Long salesAgentId;
    private Long propertyId;
    private Long clientId;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Enumerated(EnumType.STRING)
    private Currency currencyCode;
}