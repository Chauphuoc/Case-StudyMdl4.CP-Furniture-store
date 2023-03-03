package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.Product;
import com.cg.model.dto.ProductCreateReqDTO;
import com.cg.model.dto.ProductCreateResDTO;
import com.cg.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/products")
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<?> getALl() {
        List<ProductCreateResDTO> productCreateResDTOS = productService.findAllProductCreateResDTO();
        return new ResponseEntity<>(productCreateResDTOS, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductCreateResDTO> getProductByID(@PathVariable Long productId) {
        Optional<Product> productOptional = productService.findById(productId);
        if (!productOptional.isPresent()) {
            throw new DataInputException("Product is not found");
        }
        Product product = productOptional.get();
        ProductCreateResDTO productCreateResDTO = product.toProductCreateResDTO();
        return new ResponseEntity<>(productCreateResDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(ProductCreateReqDTO productCreateReqDTO) {
        ProductCreateResDTO productCreateResDTO = productService.create(productCreateReqDTO);
        return new ResponseEntity<>(productCreateResDTO, HttpStatus.CREATED);
    }


}