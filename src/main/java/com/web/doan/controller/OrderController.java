package com.web.doan.controller;

import com.web.doan.entity.CartItem;
import com.web.doan.service.CartService;
import com.web.doan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("cartItems", cartService.getCartItems());
        return "/cart/checkout";
    }
    @PostMapping("/submit")
    public String submitOrder(
            @RequestParam("customerName") String customerName,
            @RequestParam("shippingAddress") String shippingAddress,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("email") String email,
            @RequestParam("note") String note,
            @RequestParam("paymentMethod") String paymentMethod
    ) {
        List<CartItem> cartItems = cartService.getCartItems();
        if (cartItems.isEmpty()) {
            return "redirect:/cart";
        }
        orderService.createOrder(customerName, shippingAddress, phoneNumber, email, note, paymentMethod, cartItems);
        return "redirect:/order/confirmation";
    }
    @GetMapping("/confirmation")
    public String orderConfirmation(Model model){
        model.addAttribute("message", "Your order has been successfully places");
        return "cart/order-confirmation";
    }
}