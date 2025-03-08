package lk.ijse._13spring_boot.controller;

import lk.ijse._13spring_boot.dto.OrderDTO;
import lk.ijse._13spring_boot.service.OrderService;
import lk.ijse._13spring_boot.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin(origins = "http://localhost:63342")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public ResponseUtil saveOrder(@RequestBody OrderDTO orderDTO) {
        orderService.saveOrder(orderDTO);
        System.out.println("order comes");
        return new ResponseUtil(200, "Order Placed Successfully.", null);
    }

    @GetMapping("/getAll")
    public ResponseUtil getAllOrders() {
        return new ResponseUtil(200, "Retrieved Orders Successfully.", orderService.getAllOrders());
    }

    @GetMapping("/search/{id}")
    public ResponseUtil getOrderById(@PathVariable String id) {
        return new ResponseUtil(200, "Order Found Successfully", orderService.getOrderById(id));
    }

    @GetMapping("/last-id")
    public ResponseEntity<String> getLastOrderId() {
        try {
            String lastOrderId = orderService.getLastOrderId();
            System.out.println("lastOrderId = " + lastOrderId);
            return ResponseEntity.ok(lastOrderId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch last order ID");
        }
    }
}
