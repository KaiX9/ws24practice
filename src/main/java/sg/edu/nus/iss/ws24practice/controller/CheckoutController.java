package sg.edu.nus.iss.ws24practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.ws24practice.exception.OrderException;
import sg.edu.nus.iss.ws24practice.model.Cart;
import sg.edu.nus.iss.ws24practice.model.PurchaseOrder;
import sg.edu.nus.iss.ws24practice.model.PurchaseOrderDetails;
import sg.edu.nus.iss.ws24practice.repository.PurchaseOrderRepository;
import sg.edu.nus.iss.ws24practice.service.PurchaseOrderService;

@Controller
@RequestMapping
public class CheckoutController {
    
    @Autowired
    private PurchaseOrderService poSvc;

    @PostMapping(path="/checkout")
    public String postCheckout(@RequestParam String name
        , @RequestParam String ship_address, @RequestParam String notes
        , Model m, HttpSession s) throws OrderException {
        
        List<Cart> cartList = (List<Cart>) s.getAttribute("cart");
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setName(name);
        purchaseOrder.setShip_address(ship_address);
        purchaseOrder.setNotes(notes);

        PurchaseOrderDetails poDetails = (PurchaseOrderDetails) s.getAttribute("checkoutCart");
        if (poDetails.getCartList().size() > 5) {
            s.invalidate();
            throw new OrderException("Cannot order more than 5 items");
        }
        poSvc.createOrder(poDetails, purchaseOrder);
        System.out.println("Purchase Order >>> " + purchaseOrder);
        poDetails.setTotalOrderPrice(PurchaseOrderRepository.totalOrderPrice);
        System.out.println("PO details >>> " + poDetails);
        System.out.println("Total order price >>> " + poDetails.getTotalOrderPrice());

        s.invalidate();

        m.addAttribute("total", cartList.size());
        m.addAttribute("totalOrderPrice", poDetails.getTotalOrderPrice());
        return "checkout";
    }

    @ExceptionHandler(OrderException.class)
    public String handleOrderException(OrderException ex, Model m) {

        m.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}
