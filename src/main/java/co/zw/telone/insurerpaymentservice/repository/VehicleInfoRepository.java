package co.zw.telone.insurerpaymentservice.repository;

import co.zw.telone.insurerpaymentservice.model.VehicleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleInfoRepository extends JpaRepository<VehicleInfo, Long> {
    VehicleInfo findByRegistrationNumber(String registrationNumber);
}
