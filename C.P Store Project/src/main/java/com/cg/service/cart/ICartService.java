package com.cg.service.cart;

import com.cg.model.Cart;
import com.cg.model.Product;
import com.cg.model.User;
import com.cg.service.IGeneralService;

import java.util.Optional;

public interface ICartService extends IGeneralService<Cart> {

    Optional<Cart> findByUser(User user);

    void createIfNotExist(User user, Product product);

    void createIfExist(User user, Cart cart, Product product);

    void checkout(User user, Cart cart);
}