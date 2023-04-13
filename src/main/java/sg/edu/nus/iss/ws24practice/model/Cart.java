package sg.edu.nus.iss.ws24practice.model;

public class Cart {
    
    private String item;
    private Integer quantity;

    public Cart() {

    }
    
    public Cart(String item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "Cart [item=" + item + ", quantity=" + quantity + "]";
    }

    public void add() {
        this.quantity++;
    }

    public void add(Integer quantity) {
        this.quantity += quantity;
    }
    
}
