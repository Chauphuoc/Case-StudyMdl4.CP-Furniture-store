package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.model.Product;
import com.cg.model.User;
import com.cg.model.dto.ProductCreateResDTO;
import com.cg.service.cart.ICartService;
import com.cg.service.cartDetail.ICartDetailService;
import com.cg.service.product.IProductService;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/carts")
public class CartAPI {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICartDetailService cartDetailService;

    @Autowired
    private AppUtils appUtils;

//    @GetMapping
//    public ResponseEntity<?> getALlProductById () {
//        String username = appUtils.getUsernamePrincipal();
//        Optional<User> userOptional = userService.findByUsername(username);
//        User user = userOptional.get();
//        Optional<Cart> cartOptional = cartService.findByUser(user);
//
//        if (!cartOptional.isPresent()) {
//            throw new DataInputException("Cart not valid");
//        }
//        Cart cart = cartOptional.get();
//
//        List<CartDetail> cartDetails = cartDetailService.findAllByCart(cart);
//
//
//
//
//        return new ResponseEntity<>(productCreateResDTOS, HttpStatus.OK);
//    }


    @PostMapping("/{productId}")
    public ResponseEntity<?> addCart(@PathVariable Long productId) {

        String username = appUtils.getUsernamePrincipal();

        Optional<User> userOptional = userService.findByUsername(username);

        User user = userOptional.get();

        Optional<Product> productOptional = productService.findById(productId);

        if (!productOptional.isPresent()) {
            throw new DataInputException("Product not valid");
        }

        Product product = productOptional.get();

        Optional<Cart> cartOptional = cartService.findByUser(user);

        if (!cartOptional.isPresent()) {
            cartService.createIfNotExist(user, product);
        }
        else {
            Cart cart = cartOptional.get();
            cartService.createIfExist(user, cart, product);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> addCart() {
        String username = appUtils.getUsernamePrincipal();

        Optional<User> userOptional = userService.findByUsername(username);

        User user = userOptional.get();

        Optional<Cart> cartOptional = cartService.findByUser(user);

        if (!cartOptional.isPresent()) {
            throw new DataInputException("Please buy something before checkout");
        }
        else {
            Cart cart = cartOptional.get();

            cartService.checkout(user, cart);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}