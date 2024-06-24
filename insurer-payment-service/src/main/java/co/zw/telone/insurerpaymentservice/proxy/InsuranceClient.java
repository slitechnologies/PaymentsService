package co.zw.telone.insurerpaymentservice.proxy;

import co.zw.telone.insurerpaymentservice.proxy.proxymodel.Insurance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "insurer-service", url = "http://localhost:8086/api/v1/vehicle-insurance")
public interface InsuranceClient {

    @GetMapping("/{insuranceId}")
    Insurance getInsurance(@PathVariable("insuranceId") Long insuranceId);

    @GetMapping({"/{insurer-id}"})
    Long getByInsurerId(@PathVariable("insurer-id") Long insurerId);

    @GetMapping("/insurances/{insuranceId}/insurer")
    Long getInsurerByInsuranceId(@PathVariable("insuranceId") Long insuranceId);
}