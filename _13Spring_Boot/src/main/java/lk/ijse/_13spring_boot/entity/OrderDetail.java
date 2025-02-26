package lk.ijse._13spring_boot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String itemCode;

    @Column(nullable = false)
    private int qty;

    @Column(nullable = false)
    private double subTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    public OrderDetail() {
    }

    public OrderDetail(int id, String itemCode, int qty, double subTotal, Orders order) {
        this.id = id;
        this.itemCode = itemCode;
        this.qty = qty;
        this.subTotal = subTotal;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", subTotal=" + subTotal +
                ", order=" + order +
                '}';
    }
}