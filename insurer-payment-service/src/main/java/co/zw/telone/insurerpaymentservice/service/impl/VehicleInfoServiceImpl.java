package co.zw.telone.insurerpaymentservice.service.impl;

import co.zw.telone.insurerpaymentservice.dto.CreateVehicleInfoRequest;
import co.zw.telone.insurerpaymentservice.dto.CreateVehicleInfoResponse;
import co.zw.telone.insurerpaymentservice.model.VehicleInfo;
import co.zw.telone.insurerpaymentservice.repository.VehicleInfoRepository;
import co.zw.telone.insurerpaymentservice.service.VehicleInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleInfoServiceImpl  implements VehicleInfoService {
    
    private final VehicleInfoRepository vehicleInfoRepository;


    @Override
    public CreateVehicleInfoResponse createVehicleInfo(CreateVehicleInfoRequest request) {
        VehicleInfo vehicleInfo = new VehicleInfo();
        vehicleInfo.setVehicleMake(request.getVehicleMake());
        vehicleInfo.setVehicleModel(request.getVehicleModel());
        vehicleInfo.setVehicleColor(request.getVehicleColor());
        vehicleInfo.setVehicleType(request.getVehicleType());
        vehicleInfo.setEngineNumber(request.getEngineNumber());
        vehicleInfo.setVehicleUsage(request.getVehicleUsage());
        vehicleInfo.setRegistrationNumber(request.getRegistrationNumber());
        vehicleInfo.setYearOfManufacture(request.getYearOfManufacture());
        vehicleInfo.setRegistrationYear(request.getRegistrationYear());
        
        var savedVehicleInfo = vehicleInfoRepository.save(vehicleInfo);
        
        return CreateVehicleInfoResponse.builder()
                .id(savedVehicleInfo.getId())
                .vehicleMake(savedVehicleInfo.getVehicleMake())
                .vehicleModel(savedVehicleInfo.getVehicleModel())
                .vehicleColor(savedVehicleInfo.getVehicleColor())
                .vehicleUsage(savedVehicleInfo.getVehicleUsage())
                .vehicleType(savedVehicleInfo.getVehicleType())
                .engineNumber(savedVehicleInfo.getEngineNumber())
                .registrationNumber(savedVehicleInfo.getRegistrationNumber())
                .yearOfManufacture(savedVehicleInfo.getYearOfManufacture())
                .registrationYear(savedVehicleInfo.getRegistrationYear())
                .build();
        
    }

    @Override
    public CreateVehicleInfoResponse getVehicleInfoById(Long vehicleId) {
        VehicleInfo vehicleInfo = vehicleInfoRepository.findById(vehicleId).orElseThrow();


        return CreateVehicleInfoResponse.builder()
                .id(vehicleInfo.getId())
                .vehicleMake(vehicleInfo.getVehicleMake())
                .vehicleModel(vehicleInfo.getVehicleModel())
                .vehicleColor(vehicleInfo.getVehicleColor())
                .vehicleType(vehicleInfo.getVehicleType())
                .vehicleUsage(vehicleInfo.getVehicleUsage())
                .engineNumber(vehicleInfo.getEngineNumber())
                .yearOfManufacture(vehicleInfo.getYearOfManufacture())
                .yearOfManufacture(vehicleInfo.getYearOfManufacture())
                .registrationNumber(vehicleInfo.getRegistrationNumber())
                .build();
    }

    @Override
    public CreateVehicleInfoResponse getVehicleInfoByRegistration(String registrationNumber) {
        VehicleInfo byRegNumber = vehicleInfoRepository.findByRegistrationNumber(registrationNumber);

        if (byRegNumber!=null) {
            return CreateVehicleInfoResponse.builder()
                    .id(byRegNumber.getId())
                    .vehicleMake(byRegNumber.getVehicleMake())
                    .vehicleModel(byRegNumber.getVehicleModel())
                    .vehicleColor(byRegNumber.getVehicleColor())
                    .vehicleUsage(byRegNumber.getVehicleUsage())
                    .vehicleType(byRegNumber.getVehicleType())
                    .engineNumber(byRegNumber.getEngineNumber())
                    .registrationNumber(byRegNumber.getRegistrationNumber())
                    .yearOfManufacture(byRegNumber.getYearOfManufacture())
                    .registrationYear(byRegNumber.getRegistrationYear())
                    .build();
        }
        else {
            return null;
        }
    }




    @Override
    public CreateVehicleInfoResponse updateVehicleInfo(Long vehicleId, CreateVehicleInfoRequest request) {
        Optional<VehicleInfo> existingVehicleInfo = vehicleInfoRepository.findById(vehicleId);

        if (existingVehicleInfo.isPresent()) {
          VehicleInfo vehicleInfo = existingVehicleInfo.get();
          vehicleInfo.setVehicleMake(request.getVehicleMake());
          vehicleInfo.setVehicleModel(request.getVehicleModel());
          vehicleInfo.setVehicleColor(request.getVehicleColor());
          vehicleInfo.setVehicleType(request.getVehicleType());
          vehicleInfo.setEngineNumber(request.getEngineNumber());
          vehicleInfo.setVehicleUsage(request.getVehicleUsage());
          vehicleInfo.setRegistrationNumber(request.getRegistrationNumber());
          vehicleInfo.setYearOfManufacture(request.getYearOfManufacture());
          vehicleInfo.setRegistrationYear(request.getRegistrationYear());

          var savedVehicleInfo = vehicleInfoRepository.save(vehicleInfo);

          return CreateVehicleInfoResponse.builder()
                  .id(savedVehicleInfo.getId())
                  .vehicleMake(savedVehicleInfo.getVehicleMake())
                  .vehicleModel(savedVehicleInfo.getVehicleModel())
                  .vehicleColor(savedVehicleInfo.getVehicleColor())
                  .vehicleType(savedVehicleInfo.getVehicleType())
                  .vehicleUsage(savedVehicleInfo.getVehicleUsage())
                  .engineNumber(savedVehicleInfo.getEngineNumber())
                  .yearOfManufacture(savedVehicleInfo.getYearOfManufacture())
                  .registrationNumber(savedVehicleInfo.getRegistrationNumber())
                  .registrationYear(savedVehicleInfo.getRegistrationYear())
                  .build();



        } else {
            return null;
        }

    }
}