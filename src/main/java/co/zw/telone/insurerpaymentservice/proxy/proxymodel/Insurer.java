package co.zw.telone.insurerpaymentservice.proxy.proxymodel;

import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Insurer {
    private Long id;
    @Column(unique = true)
    private String insurerName;
    private String insurerLogo;
    private String address;
    private String secondAddress;
    @Column(unique = true)
    private String mobileNumber;
    private String officeNumber;
    @Column(unique = true)
    private String email;
    private String websiteUrl;
    private Boolean isActive;
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime modifiedAt;
}