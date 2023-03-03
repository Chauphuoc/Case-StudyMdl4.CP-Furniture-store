package com.cg.repository;

import com.cg.model.Product;
import com.cg.model.dto.ProductCreateResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT NEW com.cg.model.dto.ProductCreateResDTO (" +
            "p.id, " +
            "p.title, " +
            "p.price, " +
            "p.description, " +
            "p.productAvatar" +
            ") " +
            "FROM Product AS p"
    )
    List<ProductCreateResDTO> findAllProductCreateResDTO();



    @Override
    Optional<Product> findById(Long id);
}