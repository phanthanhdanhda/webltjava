package com.web.doan.controller;

import com.web.doan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public String showProductList(Model model){
        model.addAttribute("productList", productService.getAllProducts());
        return "home/shop";
   }
}
