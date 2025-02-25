package lk.ijse._13spring_boot.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int oid;
    private String date;
    private double total;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Version
    private int version;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    public Order() {
        this.orderDetails = new ArrayList<>();
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Order(int oid, String date, double total, Customer customer) {
        this.oid = (int) oid;
        this.date = date;
        this.total = total;
        this.customer = customer;
        this.orderDetails = orderDetails;
        this.orderDetails = new ArrayList<>();
    }


    public int getOid() {
        return Math.toIntExact(oid);
    }

    public void setOid(int oid) {
        this.oid = (int) oid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", date='" + date + '\'' +
                ", total=" + total +
                ", customer=" + customer +
                ", orderDetails=" + orderDetails +
                '}';
    }

}
