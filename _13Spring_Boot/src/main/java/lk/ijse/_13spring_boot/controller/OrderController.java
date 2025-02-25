package lk.ijse._13spring_boot.controller;

import lk.ijse._13spring_boot.dto.OrderDTO;
import lk.ijse._13spring_boot.service.OrderService;
import lk.ijse._13spring_boot.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin(origins = "http://localhost:63342")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/place")
    public ResponseUtil placeOrder(@RequestBody OrderDTO orderDTO){
        orderService.placeOrder(orderDTO);
        return new ResponseUtil(200, "Order placed successfully", null);
    }
    }
