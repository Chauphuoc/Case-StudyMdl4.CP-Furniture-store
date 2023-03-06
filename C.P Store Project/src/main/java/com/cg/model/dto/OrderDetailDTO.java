package com.cg.model.dto;


import com.cg.model.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailDTO {
    private Long id;
    private ProductCreateResDTO productCreateResDTO;
    private String title;
    private BigDecimal price;
    private Long quantity;
    private BigDecimal amount;
    private OrderDTO orderDTO;

    public OrderDetail toOrderDetail() {
        return new OrderDetail()
                .setId(id)
                .setProduct(productCreateResDTO.toProduct())
                .setTitle(title)
                .setPrice(price)
                .setQuantity(quantity)
                .setAmount(amount)
                .setOrder(orderDTO.toOrder());
    }
}
