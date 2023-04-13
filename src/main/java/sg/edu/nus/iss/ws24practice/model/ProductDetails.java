package sg.edu.nus.iss.ws24practice.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDetails {
    
    private Integer id;
    private String name;
    private BigDecimal standard_price;
    private BigDecimal discount;

    public static List<ProductDetails> products = new ArrayList<ProductDetails>();
    
    public ProductDetails() {
        
    }

    public ProductDetails(Integer id, String name, BigDecimal standard_price, BigDecimal discount) {
        this.id = id;
        this.name = name;
        this.standard_price = standard_price;
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getStandard_price() {
        return standard_price;
    }
    public void setStandard_price(BigDecimal standard_price) {
        this.standard_price = standard_price;
    }
    public BigDecimal getDiscount() {
        return discount;
    }
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ProductDetails [id=" + id + ", name=" + name + ", standard_price=" + standard_price + ", discount="
                + discount + "]";
    }
    
}
