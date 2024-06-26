package co.zw.telone.insurerpaymentservice.proxy.proxymodel;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateInsuranceResponse {
    private Long insuranceId;
    private String insurerName;
    private String policyTypeName;
    private Integer insuranceTerm;
    private String vehicleClassName;
    private String description;
    private BigDecimal insurancePrice;
    private Boolean isActive;
    private LocalDateTime createdAt;
}