package demo.demo.repository;

import demo.demo.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
