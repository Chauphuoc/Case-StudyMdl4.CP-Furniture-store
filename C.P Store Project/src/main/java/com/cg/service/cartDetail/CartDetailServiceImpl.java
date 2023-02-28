package com.cg.service.cartDetail;

import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.repository.CartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CartDetailServiceImpl implements ICartDetailService{

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Override
    public List<CartDetail> findALl() {
        return null;
    }

    @Override
    public Optional<CartDetail> findById(Long id) {
        return Optional.empty();
    }


    @Override
    public CartDetail save(CartDetail cartDetail) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(CartDetail cartDetail) {

    }

    @Override
    public List<CartDetail> findAllByCart(Cart cart) {
        return cartDetailRepository.findAllByCart(cart);
    }
}