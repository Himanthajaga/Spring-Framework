package lk.ijse._13spring_boot.dto;

import java.util.List;

public class OrderDTO {
    private int oid;
    private String date;
    private int customerId;
    private double total;
    private List<OrderDetailDTO> orderDetails;

    public OrderDTO() {
    }

    public OrderDTO(int oid, String date, int customerId, double total,List<OrderDetailDTO> orderDetails) {
        this.oid = oid;
        this.date = date;
        this.customerId = customerId;
        this.total = total;
        this.orderDetails = orderDetails;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getDate() {
        return date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getTotal() {
        return total;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "oid=" + oid +
                ", date='" + date + '\'' +
                ", customerId=" + customerId +
                ", total=" + total +
                '}';
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
