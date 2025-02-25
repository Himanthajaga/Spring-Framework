package lk.ijse._13spring_boot.entity;

import jakarta.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int qty;
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "oid")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "code")
    private Item item;

    public OrderDetail() {
    }

    public OrderDetail(int qty, double unitPrice, Order order, Item item) {
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.order = order;
        this.item = item;
    }
    public int getId() {
        return Math.toIntExact(id);
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setId(int id) {
        this.id = (int) id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

public Order getOrder() {
    return order;
}
public void setOrder(Order order) {
    this.order = order;
}

public Item getItem() {
    return item;
}

public void setItem(Item item) {
    this.item = item;
}

@Override
public String toString() {
    return "OrderDetail{" +
            "id=" + id +
            ", unitPrice=" + unitPrice +
            ", order=" + order +
            ", item=" + item +
            '}';
}
}