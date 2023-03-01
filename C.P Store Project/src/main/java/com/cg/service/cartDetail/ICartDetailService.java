package com.cg.service.cartDetail;

import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.model.dto.CartDetailDTO;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ICartDetailService extends IGeneralService<CartDetail> {
    List<CartDetail> findAllByCart (Cart cart);

    List<CartDetailDTO> findAllCartDetailDTO(Cart cart);

//    void deleteCartDetailById(Long id);

    @Override
    void delete(CartDetail cartDetail);

    Optional<CartDetailDTO> findCartDetailById(Long id);
}