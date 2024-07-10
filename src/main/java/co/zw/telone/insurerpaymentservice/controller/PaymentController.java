package co.zw.telone.insurerpaymentservice.controller;

import co.zw.telone.insurerpaymentservice.constants.ApiResponse;
import co.zw.telone.insurerpaymentservice.dto.*;
import co.zw.telone.insurerpaymentservice.exceptions.NotFoundException;
import co.zw.telone.insurerpaymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping
    public ResponseEntity<CreatePaymentResponse> createPayment(@RequestBody CreatePaymentRequest request) {
        CreatePaymentResponse response = paymentService.createPayment(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<CreatePaymentResponse> getPayment(@PathVariable Long paymentId) {
        try {

        CreatePaymentResponse response = paymentService.getPayment(paymentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException e) {
            // Handle the NotFoundException
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle other exceptions
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<CreatePaymentResponse>>> getAllPayments() {
        List<CreatePaymentResponse> data = paymentService.getAllPayments();
        ApiResponse<List<CreatePaymentResponse>> response = new ApiResponse<>(HttpStatus.OK, data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long paymentId) {
        paymentService.deletePayment(paymentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/ref/{reference}")
    public ResponseEntity<ApiResponse<CreatePaymentResponse>> getPaymentByReference( @PathVariable("reference") String reference) throws NotFoundException {
        CreatePaymentResponse data = paymentService.getPaymentByReference(reference);
        ApiResponse<CreatePaymentResponse> response = new ApiResponse<>(HttpStatus.OK, data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("all-payments/by-insurer/{insurer-id}")
    public ResponseEntity<ApiResponse<List<SumOfPaymentsResponse>>> getAllPaymentsByInsurer(@PathVariable("insurer-id") Long insurerId) {
        List<SumOfPaymentsResponse> data = paymentService.getPaymentsByInsurerId(insurerId);
        ApiResponse<List<SumOfPaymentsResponse>> response = new ApiResponse<>(HttpStatus.OK, data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/daily/Transactions/{date-of-transaction}")
    public ResponseEntity<ApiResponse<List<SumOfPaymentsResponse>>> getPaymentsByDateOfTransaction(@PathVariable("date-of-transaction") LocalDate dateOfTransaction) {
        List<SumOfPaymentsResponse> data = paymentService.getSumOfPaymentsByDate(dateOfTransaction);
        ApiResponse<List<SumOfPaymentsResponse>> response = new ApiResponse<>(HttpStatus.OK, data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/all-payments/currency-total/start-date/end-date")
    public ResponseEntity<ApiResponse<List<SumOfPaymentsResponse>>> getSumOfPayments(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                               @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
       List<SumOfPaymentsResponse> data = paymentService.getSumOfPaymentsByDateRange(startDate, endDate);

        ApiResponse<List<SumOfPaymentsResponse>> response = new ApiResponse<>(HttpStatus.OK, data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/tax-report/by-date")
    public ResponseEntity<ApiResponse<List<TaxReportResponse>>> getTaxReportByDate(@RequestParam("reportDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate reportDate){
        List<TaxReportResponse> data = paymentService.getTaxReportByDate(reportDate);
        ApiResponse<List<TaxReportResponse>> response = new ApiResponse<>(HttpStatus.OK, data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/total-tax/report")
    public ResponseEntity<ApiResponse<List<TaxReportResponse>>> getTotalTaxReport(){
        List<TaxReportResponse> data = paymentService.getTaxReportTotal();
        ApiResponse<List<TaxReportResponse>> response = new ApiResponse<>(HttpStatus.OK, data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}