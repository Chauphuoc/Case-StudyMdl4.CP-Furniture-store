package com.cg.model;

import com.cg.model.dto.CartDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "total_amount", precision = 10, scale = 0, nullable = false)
    private BigDecimal totalAmount;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public CartDTO toCartDTO() {
        return new CartDTO()
                .setId(id)
                .setTotalAmount(totalAmount)
                .setUser(user.toUserDTO())
                ;
    }

    public Order toOrder() {
        return new Order()
                .setId(id)
                .setUser(user)
                .setTotalAmount(totalAmount)
                ;
    }

}