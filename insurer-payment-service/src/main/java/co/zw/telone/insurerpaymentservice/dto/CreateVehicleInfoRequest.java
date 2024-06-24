package co.zw.telone.insurerpaymentservice.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateVehicleInfoRequest {
    private String vehicleMake;
    private String vehicleModel;
    private String vehicleColor;
    private String vehicleType;
    private String engineNumber;
    private String vehicleUsage;
    private String registrationNumber;
    private LocalDate yearOfManufacture;
    private String registrationYear;
}