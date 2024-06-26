package co.zw.telone.insurerpaymentservice.service;


import co.zw.telone.insurerpaymentservice.dto.CreateVehicleInfoRequest;
import co.zw.telone.insurerpaymentservice.dto.CreateVehicleInfoResponse;

public interface VehicleInfoService {

    CreateVehicleInfoResponse createVehicleInfo(CreateVehicleInfoRequest request);
    CreateVehicleInfoResponse getVehicleInfoById(Long vehicleId);
    CreateVehicleInfoResponse getVehicleInfoByRegistration(String vehicleId);
    CreateVehicleInfoResponse updateVehicleInfo(Long vehicleId, CreateVehicleInfoRequest request);
}
