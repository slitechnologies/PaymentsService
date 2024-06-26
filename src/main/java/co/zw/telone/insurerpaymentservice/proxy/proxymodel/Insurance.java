package co.zw.telone.insurerpaymentservice.proxy.proxymodel;

import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insurance {
        private Long id;
        private Insurer insurer;
        private Long policyType;
        private Integer insuranceTerm;
        private BigDecimal insurancePrice;
        private Boolean isActive;
        private String insuranceDescription;

        private Long vehicleClassId;
        private List<Long> addOn;
        @CreationTimestamp
        @Column(updatable = false)
        private LocalDateTime createdAt;
        @UpdateTimestamp
        @Column(insertable = false)
        private LocalDateTime updatedAt;
    }