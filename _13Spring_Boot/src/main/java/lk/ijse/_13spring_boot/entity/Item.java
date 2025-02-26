package lk.ijse._13spring_boot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Item {
    @Id
    private String itemcode;
    private String description;
    private double unitPrice;
    private int qtyOnHand;

    public Item() {
    }

    public Item(int code, String description, double unitPrice, int qtyOnHand) {
        this.itemcode = String.valueOf(code);
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }

    public String getCode() {
        return itemcode;
    }

    public String getDescription() {
        return description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setCode(int code) {
        this.itemcode = String.valueOf(code);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code=" +itemcode+
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }
}
