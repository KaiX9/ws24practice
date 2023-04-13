package sg.edu.nus.iss.ws24practice.repository;

public class DBQueries {
    
    public static final String GET_ALL_PRODUCTS = "select * from fruits_products";

    public static final String INSERT_PURCHASE_ORDER = "insert into purchase_order(order_id, order_date, customer_name, ship_address, notes, tax) values (?, SYSDATE(), ?, ?, ?, 0.05)";

    public static final String INSERT_PURCHASE_ORDER_DETAILS = "insert into purchase_order_details(product, unit_price, discount, quantity, order_id) values (?, ?, ?, ?, ?)";

    public static final String GET_TOTAL_ORDER_PRICE = "select sum(unit_price) from purchase_order_details where order_id = ?";

}
