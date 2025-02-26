package lk.ijse._13spring_boot.repository;

import lk.ijse._13spring_boot.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetail, Integer> {
}
