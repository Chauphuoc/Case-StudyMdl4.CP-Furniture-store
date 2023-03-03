package com.cg.model;

import com.cg.model.dto.OrderDTO;
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
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "total_amount", precision = 10, scale = 0, nullable = false)
    private BigDecimal totalAmount;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public OrderDTO toOrderDTO() {
        return new OrderDTO()
                .setId(id)
                .setTotalAmount(totalAmount)
                .setUserDTO(user.toUserDTO());
    }


}