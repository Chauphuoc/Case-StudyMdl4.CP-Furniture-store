package com.cg.model;


import com.cg.model.dto.ProductCreateResDTO;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal price;

    private String description;

    @OneToOne
    @JoinColumn(name = "product_avatar_id", referencedColumnName = "id", nullable = false)
    private ProductAvatar productAvatar;


    public ProductCreateResDTO toProductCreateResDTO() {
        return new ProductCreateResDTO()
                .setId(id)
                .setTitle(title)
                .setPrice(price)
                .setDescription(description)
                .setProductAvatarDTO(productAvatar.toProductAvatarDTO())
                ;
    }

}