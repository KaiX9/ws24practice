package sg.edu.nus.iss.ws24practice.model;

import java.util.Date;

public class PurchaseOrder {
    
    private Integer order_id;
    private Date order_date;
    private String name;
    private String ship_address;
    private String notes;
    
    public PurchaseOrder() {
        
    }

    public PurchaseOrder(Integer order_id, Date order_date, String name, String ship_address, String notes) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.name = name;
        this.ship_address = ship_address;
        this.notes = notes;
    }

    public Integer getOrder_id() {
        return order_id;
    }
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
    public Date getOrder_date() {
        return order_date;
    }
    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShip_address() {
        return ship_address;
    }
    public void setShip_address(String ship_address) {
        this.ship_address = ship_address;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "PurchaseOrder [order_id=" + order_id + ", order_date=" + order_date + ", name=" + name
                + ", ship_address=" + ship_address + ", notes=" + notes + "]";
    }

}
