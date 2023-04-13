package sg.edu.nus.iss.ws24practice.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.ws24practice.model.Cart;
import sg.edu.nus.iss.ws24practice.model.ProductDetails;
import sg.edu.nus.iss.ws24practice.model.PurchaseOrderDetails;
import sg.edu.nus.iss.ws24practice.service.PurchaseOrderService;

import static sg.edu.nus.iss.ws24practice.repository.DBQueries.*;

@Repository
public class CartRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ProductDetails> getProducts() {

        SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_ALL_PRODUCTS);
        List<ProductDetails> products = new ArrayList<ProductDetails>();

        while (rs.next()) {
            ProductDetails product = new ProductDetails();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setStandard_price(rs.getBigDecimal("standard_price"));
            product.setDiscount(rs.getBigDecimal("discount"));
            products.add(product);
        }

        return products;
    }

    public void addCart(List<Cart> cartList, PurchaseOrderDetails poDetails) {

        List<Object[]> data = new ArrayList<Object[]>();
        for (Cart i : cartList) {
            Object[] values = new Object[] {
                i.getItem(),
                PurchaseOrderService.calculateUnitPrice(i.getItem(), i.getQuantity()),
                poDetails.getDiscount(),
                i.getQuantity(),
                poDetails.getOrder_id()
            };
            poDetails.setItem(i.getItem());
            poDetails.setUnitPrice(PurchaseOrderService.calculateUnitPrice(i.getItem(), i.getQuantity()));
            poDetails.setDiscount(PurchaseOrderService.discount);
            poDetails.setQuantity(i.getQuantity());            
            data.add(values);
        }

        jdbcTemplate.batchUpdate(INSERT_PURCHASE_ORDER_DETAILS, data);
    }

}
