package lk.ijse._13spring_boot.service.impl;

import lk.ijse._13spring_boot.dto.OrderDTO;
import lk.ijse._13spring_boot.dto.OrderDetailDTO;
import lk.ijse._13spring_boot.entity.Customer;
import lk.ijse._13spring_boot.entity.Item;
import lk.ijse._13spring_boot.entity.Order;
import lk.ijse._13spring_boot.entity.OrderDetail;
import lk.ijse._13spring_boot.repository.CustomerRepo;
import lk.ijse._13spring_boot.repository.ItemRepo;
import lk.ijse._13spring_boot.repository.OrderRepo;
import lk.ijse._13spring_boot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public void placeOrder(OrderDTO order) {
        logger.info("Received OrderDTO: " + order);

        if (order.getCustomerId() <= 0) {
            logger.severe("Invalid customer ID: " + order.getCustomerId());
            throw new RuntimeException("Invalid customer ID");
        }

        Optional<Customer> customerOptional = customerRepo.findById(order.getCustomerId());
        if (customerOptional.isEmpty()) {
            logger.severe("Customer not found with ID: " + order.getCustomerId());
            throw new RuntimeException("Customer not found");
        }

        Order o = new Order();
        o.setOid(order.getOid());
        o.setDate(order.getDate());
        o.setCustomer(customerOptional.get());

        // Process order details
        o.setOrderDetails(new ArrayList<>());
        for (OrderDetailDTO od : order.getOrderDetails()) {
            Optional<Item> itemOptional = itemRepo.findById(od.getItemCode());
            if (itemOptional.isPresent()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setItem(itemOptional.get());
                orderDetail.setQty(od.getQty());
                orderDetail.setUnitPrice(od.getUnitPrice());
                orderDetail.setOrder(o);
                o.getOrderDetails().add(orderDetail);
            } else {
                logger.severe("Item not found with code: " + od.getItemCode());
                throw new RuntimeException("Item not found");
            }
        }

        try {
            orderRepo.save(o);
            logger.info("Order placed successfully with ID: " + o.getOid());
        } catch (ObjectOptimisticLockingFailureException e) {
            logger.severe("Failed to place the order. Please try again");
            throw new RuntimeException("Failed to place the order. Please try again");
        }
    }
}