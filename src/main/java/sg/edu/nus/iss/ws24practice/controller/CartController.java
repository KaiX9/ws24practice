package sg.edu.nus.iss.ws24practice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.ws24practice.exception.OrderException;
import sg.edu.nus.iss.ws24practice.model.Cart;
import sg.edu.nus.iss.ws24practice.model.PurchaseOrderDetails;

@Controller
@RequestMapping
public class CartController {
    
    @PostMapping(path="/order")
    public String postCart(Model m, HttpSession s, @ModelAttribute Cart cart) throws OrderException {

        List<Cart> cartList = (List<Cart>)s.getAttribute("cart");

        if (null == cartList) {
            cartList = new ArrayList<Cart>();
            s.setAttribute("cart", cartList);
        }

        List<Cart> currentCart = cartList.stream()
                                .filter(i -> i.getItem().equals(cart.getItem()))
                                .toList();
        if (currentCart.isEmpty()) {
            cartList.add(cart);
        } else {
            currentCart.get(0).add(cart.getQuantity());
        }
        
        PurchaseOrderDetails poDetails = new PurchaseOrderDetails();
        poDetails.setCartList(cartList);
        System.out.println("Cart >>> " + poDetails.getCartList());

        s.setAttribute("checkoutCart", poDetails);
        m.addAttribute("cartList", cartList);
        return "cart_template";
    }
    
}
