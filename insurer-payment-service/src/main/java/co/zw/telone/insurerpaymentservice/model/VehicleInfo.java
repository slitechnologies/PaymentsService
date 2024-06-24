package co.zw.telone.insurerpaymentservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class VehicleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vehicleMake;
    private String vehicleModel;
    private String vehicleColor;
    private String vehicleType;
    private String engineNumber;
    private String vehicleUsage;
    @Column(unique = true)
    private String registrationNumber;
    private LocalDate yearOfManufacture;
    private String registrationYear;
}