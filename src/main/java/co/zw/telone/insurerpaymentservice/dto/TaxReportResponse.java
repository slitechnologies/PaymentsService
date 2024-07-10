package co.zw.telone.insurerpaymentservice.dto;

import co.zw.telone.insurerpaymentservice.constants.Currency;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaxReportResponse {
    private String policyName;
    private String categoryName;
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private Currency currencyCode;
    private BigDecimal taxAmount;
}
