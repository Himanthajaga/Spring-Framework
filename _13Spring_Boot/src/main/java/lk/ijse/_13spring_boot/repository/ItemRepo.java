package lk.ijse._13spring_boot.repository;

import lk.ijse._13spring_boot.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item,String> {
}
