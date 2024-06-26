package co.zw.telone.insurerpaymentservice.dto;

import lombok.*;
import java.math.BigDecimal;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TotalPaymentResponse {
    private String policyName;
    private String categoryName;
    private BigDecimal totalUsdAmount;
    private BigDecimal totalZigAmount;
}