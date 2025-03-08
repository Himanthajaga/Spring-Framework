package lk.ijse._13spring_boot.service;

import lk.ijse._13spring_boot.dto.OrderDTO;
import lk.ijse._13spring_boot.dto.OrderDetailDTO;

import java.util.List;

public interface OrderService {
    void saveOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(String orderId);

    List<OrderDTO> getAllOrders();

    boolean checkItemsInStock(List<OrderDetailDTO> orderDetails);
    String getLastOrderId();
}
