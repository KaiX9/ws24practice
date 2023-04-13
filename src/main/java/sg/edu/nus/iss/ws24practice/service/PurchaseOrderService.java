package sg.edu.nus.iss.ws24practice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.ws24practice.exception.OrderException;
import sg.edu.nus.iss.ws24practice.model.ProductDetails;
import sg.edu.nus.iss.ws24practice.model.PurchaseOrder;
import sg.edu.nus.iss.ws24practice.model.PurchaseOrderDetails;
import sg.edu.nus.iss.ws24practice.repository.CartRepository;
import sg.edu.nus.iss.ws24practice.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {
    
    @Autowired
    private PurchaseOrderRepository poRepo;
    @Autowired
    private CartRepository cartRepo;

    public static BigDecimal standardPrice;
    public static BigDecimal discount;

    @Transactional(rollbackFor = OrderException.class)
    public void createOrder(PurchaseOrderDetails poDetails, PurchaseOrder purchaseOrder) {

        String uuid = UUID.randomUUID().toString();
        String orderId = uuid.replaceAll("[^0-9]", "").substring(0, 8);
        System.out.println("Order ID >>> " + orderId);
        purchaseOrder.setOrder_id(Integer.parseInt(orderId));
        poDetails.setOrder_id(Integer.parseInt(orderId));

        ProductDetails.products = cartRepo.getProducts();

        poRepo.insertPurchaseOrder(purchaseOrder);
        cartRepo.addCart(poDetails.getCartList(), poDetails);
        System.out.println("Cart at create order >>> " + poDetails.getCartList());
        poRepo.getTotalForOrderId(poDetails.getOrder_id());
        System.out.println("total order price at svc >>> " + PurchaseOrderRepository.totalOrderPrice);
        
    }

    public static BigDecimal calculateUnitPrice(String item, Integer quantity) {

        BigDecimal one = new BigDecimal(1);
        BigDecimal tax = new BigDecimal(0.05);
        standardPrice = new BigDecimal(0.00);
        discount = new BigDecimal(0.00);
        
        List<ProductDetails> products = ProductDetails.products;
        PurchaseOrderDetails poDetails = new PurchaseOrderDetails();
        for (ProductDetails p : products) {
            if (p.getName().equalsIgnoreCase(item)) {
                standardPrice = p.getStandard_price();
                discount = p.getDiscount();
            }
            System.out.println("Standard price for " + item + ">>> " + standardPrice);
            System.out.println("Discount for" + item + ">>> " + discount);
        }

        BigDecimal totalPrice = BigDecimal.valueOf(quantity).multiply(standardPrice);
        BigDecimal priceAfterDiscount;
        if (discount.compareTo(one) == 0) {
            priceAfterDiscount = totalPrice;
        } else {
            priceAfterDiscount = (poDetails.getUnitPrice().multiply(one.subtract(discount)))
                                        .multiply(BigDecimal.valueOf(quantity));
        }
        
        BigDecimal priceAfterTax = priceAfterDiscount.add(priceAfterDiscount.multiply(tax));
        priceAfterTax = priceAfterTax.setScale(2, RoundingMode.DOWN).stripTrailingZeros();
        System.out.println("Unit Price: " + priceAfterTax);

        return priceAfterTax;
    }
}
