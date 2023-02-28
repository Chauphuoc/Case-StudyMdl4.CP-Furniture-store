package com.cg.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String showListPage() {
        return "customer/index";
    }

    @GetMapping("/bill")
    public String showBillPage() {
        return "product/bill";
    }
}

