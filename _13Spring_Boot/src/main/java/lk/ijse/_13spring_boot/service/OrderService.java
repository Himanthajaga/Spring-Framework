package lk.ijse._13spring_boot.service;

import lk.ijse._13spring_boot.dto.OrderDTO;

public interface OrderService {
    void placeOrder(OrderDTO orderDTO);
}
