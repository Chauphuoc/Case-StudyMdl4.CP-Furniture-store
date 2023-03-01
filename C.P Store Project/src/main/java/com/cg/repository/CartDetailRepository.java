package com.cg.repository;

import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.model.Product;
//import com.cg.model.dto.CartDetailDTO;
import com.cg.model.dto.CartDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    @Query("SELECT NEW com.cg.model.dto.CartDetailDTO (" +
            "c.id, " +
            "c.title, " +
            "c.price, " +
            "c.quantity," +
            "c.amount, " +
            "c.product , " +
            "c.cart" +
            ")" +
            "FROM CartDetail AS c " +
            "WHERE c.cart = :cart"
    )
    List<CartDetailDTO> findAllCartDetailDTO(@Param("cart") Cart cart);

    Optional<CartDetail> findByCartAndProduct(Cart cart, Product product);


    Optional<CartDetailDTO> findCartDetailById (Long id);

    void deleteCartDetailById(Long id);

    List<CartDetail> findAllByCart(Cart cart);

    @Query("SELECT SUM(cd.amount) FROM CartDetail AS cd WHERE cd.cart = :cart")
    BigDecimal getTotalAmountByCart(@Param("cart") Cart cart);

    void deleteAllByCart(Cart cart);
}
