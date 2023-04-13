package sg.edu.nus.iss.ws24practice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.ws24practice.model.PurchaseOrder;
import sg.edu.nus.iss.ws24practice.model.PurchaseOrderDetails;

import static sg.edu.nus.iss.ws24practice.repository.DBQueries.*;

import java.math.BigDecimal;

@Repository
public class PurchaseOrderRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static BigDecimal totalOrderPrice;

    public boolean insertPurchaseOrder(PurchaseOrder purchaseOrder) {

        return jdbcTemplate.update(INSERT_PURCHASE_ORDER, purchaseOrder.getOrder_id()
            , purchaseOrder.getName(), purchaseOrder.getShip_address()
            , purchaseOrder.getNotes()) > 0;
    }

    public BigDecimal getTotalForOrderId(Integer order_id) {

        SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_TOTAL_ORDER_PRICE, order_id);
        PurchaseOrderDetails poDetails = new PurchaseOrderDetails();
        while (rs.next()) {
            poDetails = new PurchaseOrderDetails();
            poDetails.setTotalOrderPrice(rs.getBigDecimal("sum(unit_price)"));
            totalOrderPrice = poDetails.getTotalOrderPrice();
            System.out.println("total price >>> " + totalOrderPrice);
        }
        return totalOrderPrice;
    }
}
