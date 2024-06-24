package co.zw.telone.insurerpaymentservice.controller;

import co.zw.telone.insurerpaymentservice.dto.CreateVehicleInfoRequest;
import co.zw.telone.insurerpaymentservice.dto.CreateVehicleInfoResponse;
import co.zw.telone.insurerpaymentservice.service.VehicleInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/vehicle_info")
@RequiredArgsConstructor
public class VehicleInfoController {

    private final VehicleInfoService vehicleInfoService;

    @PostMapping
    ResponseEntity<CreateVehicleInfoResponse> addVehicleInfo(@RequestBody CreateVehicleInfoRequest request) {
        CreateVehicleInfoResponse data = vehicleInfoService.createVehicleInfo(request);
        return ResponseEntity.status(201).body(data);
    }

    @GetMapping("/{vehicle_id}")
    public ResponseEntity<CreateVehicleInfoResponse> getVehicleInfoById(@PathVariable("vehicle_id") Long vehicleId) {
        CreateVehicleInfoResponse data = vehicleInfoService.getVehicleInfoById(vehicleId);
        return ResponseEntity.status(200).body(data);
    }

    @PutMapping("/{vehicle_id}")
    ResponseEntity<CreateVehicleInfoResponse> updateVehicleInfo(@PathVariable("vehicle_id") Long vehicleId ,@RequestBody CreateVehicleInfoRequest request) {
        CreateVehicleInfoResponse data = vehicleInfoService.updateVehicleInfo(vehicleId, request);
        return ResponseEntity.status(201).body(data);
    }

    @GetMapping("/registration/{vehicle-registration}")
    public ResponseEntity<CreateVehicleInfoResponse> getVehicleByRegistrationNumber(@PathVariable("vehicle-registration") String registrationNumber){
        CreateVehicleInfoResponse data = vehicleInfoService.getVehicleInfoByRegistration(registrationNumber);
        return  ResponseEntity.status(HttpStatus.OK).body(data);
    }

}
