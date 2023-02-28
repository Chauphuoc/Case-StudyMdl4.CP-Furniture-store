package com.cg.service.cartDetail;

import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.service.IGeneralService;

import java.util.List;

public interface ICartDetailService extends IGeneralService<CartDetail> {
    List<CartDetail> findAllByCart (Cart cart);
}