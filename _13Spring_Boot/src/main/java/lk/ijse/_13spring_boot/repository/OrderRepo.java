package lk.ijse._13spring_boot.repository;

import lk.ijse._13spring_boot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {
}
