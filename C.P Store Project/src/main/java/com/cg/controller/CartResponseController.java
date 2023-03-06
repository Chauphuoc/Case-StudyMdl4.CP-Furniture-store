package com.cg.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/responseCart")
public class CartResponseController {
    @GetMapping
    public String getResponseCart() {
        return "customer/order";
    }
}
