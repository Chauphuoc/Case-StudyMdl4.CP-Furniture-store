package com.cg.service.product;

import com.cg.model.Product;
import com.cg.model.dto.ProductCreateReqDTO;
import com.cg.model.dto.ProductCreateResDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    List<ProductCreateResDTO> findAllProductCreateResDTO();

    ProductCreateResDTO create(ProductCreateReqDTO productCreateReqDTO);
}