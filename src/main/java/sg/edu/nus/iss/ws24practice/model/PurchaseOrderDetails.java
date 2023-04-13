package sg.edu.nus.iss.ws24practice.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderDetails {
    
    // private Cart cart;
    private String item;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal discount;
    private Integer order_id;
    private BigDecimal totalOrderPrice;

    private List<Cart> cartList = new ArrayList<Cart>();
    
    public PurchaseOrderDetails() {

    }
    
    public PurchaseOrderDetails(String item, BigDecimal unitPrice, BigDecimal discount, Integer quantity, Integer order_id) {
        // this.cart = cart;
        this.item = item;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.quantity = quantity;
        this.order_id = order_id;
    }
    
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // public Cart getCart() {
    //     return cart;
    // }
    // public void setCart(Cart cart) {
    //     this.cart = cart;
    // }
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    public BigDecimal getDiscount() {
        return discount;
    }
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
    public Integer getOrder_id() {
        return order_id;
    }
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "PurchaseOrderDetails [item=" + item + ", unitPrice=" + unitPrice + ", discount=" + discount
                + ", quantity=" + quantity + ", order_id=" + order_id + ", totalOrderPrice=" + totalOrderPrice + "]";
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public BigDecimal getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(BigDecimal totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }
    
}
