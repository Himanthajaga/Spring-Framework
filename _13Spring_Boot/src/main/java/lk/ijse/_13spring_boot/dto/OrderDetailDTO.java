package lk.ijse._13spring_boot.dto;

public class OrderDetailDTO {
    private int id;
    private String itemCode;
    private int qty;
    private double subTotal;
    private String orderId;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int id, String itemCode, int qty, double subTotal, String orderId) {
        this.id = id;
        this.itemCode = itemCode;
        this.qty = qty;
        this.subTotal = subTotal;
        this.orderId = orderId;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "id=" + id +
                ", itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", subTotal=" + subTotal +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
