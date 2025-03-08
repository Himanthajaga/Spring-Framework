package lk.ijse._13spring_boot.repository;

import lk.ijse._13spring_boot.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Orders, String> {
    @Query("SELECT o.orderId FROM Orders o ORDER BY o.orderId DESC")
    String findLastOrderId();
}
