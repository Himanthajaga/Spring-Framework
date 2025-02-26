package lk.ijse._13spring_boot.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    private String orderId;

    @Column(nullable = false)
    private String dateTime;

    @Column(nullable = false)
    private String customerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Orders() {
    }

    public Orders(String orderId, String dateTime, String customerId, List<OrderDetail> orderDetails) {
        this.orderId = orderId;
        this.dateTime = dateTime;
        this.customerId = customerId;
        this.orderDetails = orderDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId='" + orderId + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
