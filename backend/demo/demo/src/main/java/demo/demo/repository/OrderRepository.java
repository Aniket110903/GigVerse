package demo.demo.repository;

import demo.demo.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByBuyerId(UUID id);

    List<Order> findBySellerId(UUID id);
}
