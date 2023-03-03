package com.cg.model.dto;


import com.cg.model.Order;
import com.cg.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private Long id;
    private BigDecimal totalAmount;
    private UserDTO userDTO;

    public Order toOrder() {
        return new Order()
                .setId(id)
                .setTotalAmount(totalAmount)
                .setUser(userDTO.toUser());
    }
}
