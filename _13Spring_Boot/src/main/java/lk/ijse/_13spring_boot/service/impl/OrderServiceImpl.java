package lk.ijse._13spring_boot.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse._13spring_boot.dto.OrderDTO;
import lk.ijse._13spring_boot.dto.OrderDetailDTO;
import lk.ijse._13spring_boot.entity.Item;
import lk.ijse._13spring_boot.entity.OrderDetail;
import lk.ijse._13spring_boot.entity.Orders;
import lk.ijse._13spring_boot.repository.ItemRepo;
import lk.ijse._13spring_boot.repository.OrderDetailsRepo;
import lk.ijse._13spring_boot.repository.OrderRepo;
import lk.ijse._13spring_boot.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.logging.Logger;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo ordersRepo;
    
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    @Override
    @Transactional
    public void saveOrder(OrderDTO orderDTO) {
        try {
            // Validation
            if (orderDTO == null || orderDTO.getOrderId() == null ||
                    orderDTO.getOrderDetails() == null || orderDTO.getOrderDetails().isEmpty()) {
                throw new RuntimeException("Invalid order data. Please check.");
            }

            if (ordersRepo.existsById(orderDTO.getOrderId())) {
                throw new RuntimeException("Order ID already exists.");
            }

            if (!checkItemsInStock(orderDTO.getOrderDetails())) {
                throw new RuntimeException("Items are out of stock.");
            }

            // Generate next order ID
            String lastOrderId = ordersRepo.findLastOrderId();
            String nextOrderId = generateNextOrderId(lastOrderId);
            orderDTO.setOrderId(nextOrderId);

            // Create Order
            Orders order = new Orders();
            order.setOrderId(orderDTO.getOrderId());
            order.setDateTime(orderDTO.getDateTime() != null ?
                    orderDTO.getDateTime() :
                    LocalDateTime.now().toString());
            order.setCustomerId(orderDTO.getCustomerId());

            // Create OrderDetails and update item quantities
            List<OrderDetail> orderDetails = new ArrayList<>();

            for (OrderDetailDTO detailDTO : orderDTO.getOrderDetails()) {
                // Get item and check stock
                Item item = itemRepo.findById(detailDTO.getItemCode())
                        .orElseThrow(() -> new RuntimeException("Item not found: " + detailDTO.getItemCode()));

                // Create order detail
                OrderDetail detail = new OrderDetail();
                detail.setItemCode(detailDTO.getItemCode());
                detail.setQty(detailDTO.getQty());
                detail.setSubTotal(detailDTO.getSubTotal());
                detail.setOrder(order);
                orderDetails.add(detail);

                // Update item quantity
                item.setQtyOnHand(item.getQtyOnHand() - detailDTO.getQty());
                itemRepo.save(item);
            }

            order.setOrderDetails(orderDetails);
            ordersRepo.save(order);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save order: " + e.getMessage());
        }
    }

    private String generateNextOrderId(String lastOrderId) {
        if (lastOrderId == null || lastOrderId.isEmpty()) {
            return "ORD-001";
        }
        int lastIdNum = Integer.parseInt(lastOrderId.replace("ORD-", ""));
        int nextIdNum = lastIdNum + 1;
        return String.format("ORD-%03d", nextIdNum);
    }

    @Override
    public boolean checkItemsInStock(List<OrderDetailDTO> orderDetails) {
        for (OrderDetailDTO detail : orderDetails) {
            logger.info("Checking stock for item: " + detail.getItemCode());
            Optional<Item> itemOpt = itemRepo.findById(detail.getItemCode());
            if (itemOpt.isEmpty()) {
                throw new RuntimeException("Item not found: " + detail.getItemCode());
            }
            Item item = itemOpt.get();
            if (item.getQtyOnHand() < detail.getQty()) {
                throw new RuntimeException("Item " + item.getDescription() + " is out of stock.");
            }
        }
        return true;
    }

    @Override
    @Transactional
    public OrderDTO getOrderById(String orderId) {
        return ordersRepo.findById(orderId)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    @Transactional
    public List<OrderDTO> getAllOrders() {
        return ordersRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private OrderDTO mapToDTO(Orders order) {
        List<OrderDetailDTO> orderDetailDTOs = order.getOrderDetails().stream()
                .map(this::mapToDetailDTO)
                .collect(Collectors.toList());

        return new OrderDTO(
                order.getOrderId(),
                order.getDateTime(),
                order.getCustomerId(),
                orderDetailDTOs
        );
    }

    private OrderDetailDTO mapToDetailDTO(OrderDetail detail) {
        return new OrderDetailDTO(
                detail.getId(),
                detail.getItemCode(),
                detail.getQty(),
                detail.getSubTotal(),
                detail.getOrder().getOrderId()
        );
    }

    @Override
    public String getLastOrderId() {
        return ordersRepo.findLastOrderId();
    }
}