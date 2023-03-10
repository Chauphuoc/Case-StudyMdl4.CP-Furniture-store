package com.cg.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartResponseDTO {
    private Long id;
    private String title;
    private BigDecimal price;
    private Long quantity;
    private BigDecimal amount;
    private String fullName;
    private String phone;
    private LocationRegionDTO locationRegionDTO;

}
