package co.zw.telone.insurerpaymentservice.repository;

import co.zw.telone.insurerpaymentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByReferenceNumber(String reference);

    List<Payment> findByInsurerId(Long insurerId);

//    List<Payment> findByCreatedAt(LocalDate createdAt);

    List<Payment> findByCreatedAt(LocalDate dateCreated);
}
