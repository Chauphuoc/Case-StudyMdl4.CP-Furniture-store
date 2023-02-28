package com.cg.service.cart;

import com.cg.model.*;
import com.cg.repository.CartDetailRepository;
import com.cg.repository.CartRepository;
import com.cg.repository.OrderDetailRepository;
import com.cg.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CartServiceImpl implements ICartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    public List<Cart> findALl() {
        return null;
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Cart> findByUser(User user) {
        return cartRepository.findByUser(user);
    }


    @Override
    public void createIfNotExist(User user, Product product) {
        Cart cart = new Cart();
        cart.setUser(user);

        String productTitle = product.getTitle();
        BigDecimal productPrice = product.getPrice();
        long quantity = 1L;
        BigDecimal productAmount = productPrice.multiply(BigDecimal.valueOf(quantity));

        cart.setTotalAmount(productAmount);

        cartRepository.save(cart);

        CartDetail cartDetail = new CartDetail();
        cartDetail.setProduct(product);
        cartDetail.setTitle(productTitle);
        cartDetail.setPrice(productPrice);
        cartDetail.setQuantity(quantity);
        cartDetail.setAmount(productAmount);
        cartDetail.setCart(cart);

        cartDetailRepository.save(cartDetail);
    }

    @Override
    public void createIfExist(User user, Cart cart, Product product) {
        Optional<CartDetail> cartDetailOptional = cartDetailRepository.findByCartAndProduct(cart, product);

        String productTitle = product.getTitle();
        BigDecimal productPrice = product.getPrice();
        long quantity = 1L;
        BigDecimal productAmount = productPrice.multiply(BigDecimal.valueOf(quantity));

        if (!cartDetailOptional.isPresent()) {
            CartDetail cartDetail = new CartDetail();
            cartDetail.setCart(cart);
            cartDetail.setProduct(product);
            cartDetail.setTitle(productTitle);
            cartDetail.setPrice(productPrice);
            cartDetail.setQuantity(quantity);
            cartDetail.setAmount(productAmount);
            cartDetailRepository.save(cartDetail);
        }
        else {
            CartDetail cartDetail = cartDetailOptional.get();
            long newQuantity = cartDetail.getQuantity() + quantity;
            BigDecimal newAmount = productPrice.multiply(BigDecimal.valueOf(newQuantity));
            cartDetail.setPrice(productPrice);
            cartDetail.setQuantity(newQuantity);
            cartDetail.setAmount(newAmount);
            cartDetailRepository.save(cartDetail);
        }

        BigDecimal newTotalAmount = cartDetailRepository.getTotalAmountByCart(cart);
        cart.setTotalAmount(newTotalAmount);
        cartRepository.save(cart);
    }

    @Override
    public void checkout(User user, Cart cart) {
        Order order = cart.toOrder();
        order.setId(null);
        orderRepository.save(order);

        List<CartDetail> cartDetails = cartDetailRepository.findAllByCart(cart);

        for (CartDetail item : cartDetails) {
            OrderDetail orderDetail = item.toOrderDetail(order);
            orderDetail.setId(null);
            orderDetailRepository.save(orderDetail);
        }

        cartDetailRepository.deleteAllByCart(cart);
        cartRepository.deleteById(cart.getId());

    }

    @Override
    public Cart save(Cart cart) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Cart cart) {

    }


}