package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.model.dto.CartDetailDTO;
import com.cg.model.dto.CartResponseDTO;
import com.cg.model.dto.CustomerDTO;
import com.cg.model.dto.ProductCreateResDTO;
import com.cg.repository.CartDetailRepository;
import com.cg.repository.CartRepository;
import com.cg.service.cart.ICartService;
import com.cg.service.cartDetail.ICartDetailService;
import com.cg.service.customer.ICustomerService;
import com.cg.service.order.IOrderService;
import com.cg.service.product.IProductService;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/carts")
public class CartAPI {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICartDetailService cartDetailService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private AppUtils appUtils;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartDetailRepository cartDetailRepository;

    @GetMapping
    public ResponseEntity<?> getCartDetail () {
        String username = appUtils.getUsernamePrincipal();
        Optional<User> userOptional = userService.findByUsername(username);
        User user = userOptional.get();
        Optional<Cart> cartOptional = cartService.findByUser(user);

        if (!cartOptional.isPresent()) {
            throw new DataInputException("Cart not valid");
        }
        Cart cart = cartOptional.get();

        List<CartDetailDTO> cartDetailDTOS = cartDetailService.findAllCartDetailDTO(cart);
        return new ResponseEntity<>(cartDetailDTOS, HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> doDelete(@PathVariable Long id) {
        Optional<CartDetail> cartDetailOptional = cartDetailService.findById(id);
        if (!cartDetailOptional.isPresent()) {
            throw new DataInputException("CartDetail is not found");
        }
//        CartDetailDTO cartDetailDTO = cartDetailDTOOptional.get();
//        CartDetail cartDetail = cartDetailDTO.toCartDetail();
        cartDetailService.deleteById(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }



    @PostMapping("/add/{productId}")
    public ResponseEntity<?> addCart(@PathVariable Long productId) {

        String username = appUtils.getUsernamePrincipal();

        Optional<User> userOptional = userService.findByUsername(username);

        User user = userOptional.get();

        Optional<Product> productOptional = productService.findById(productId);

        if (!productOptional.isPresent()) {
            throw new DataInputException("Product not valid");
        }

        Product product = productOptional.get();

        Optional<Cart> cartOptional = cartService.findByUser(user);

        if (!cartOptional.isPresent()) {
            cartService.createIfNotExist(user, product);
        }
        else {
            Cart cart = cartOptional.get();
            cartService.createIfExist(user, cart, product);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> addCart() {
        String username = appUtils.getUsernamePrincipal();

        Optional<User> userOptional = userService.findByUsername(username);

        User user = userOptional.get();

        Customer customer = customerService.findCustomerByUser(user);
        CustomerDTO customerDTO = customer.toCustomerDTO();

        Optional<Cart> cartOptional = cartService.findByUser(user);


        List<CartResponseDTO> cartResponseDTOList = new ArrayList<>();
        if (!cartOptional.isPresent()) {
            throw new DataInputException("Please buy something before checkout");
        }
        else {
            Cart cart = cartOptional.get();

            List<CartDetailDTO> cartDetailDTOS = cartDetailRepository.findAllCartDetailDTO(cart);


            for (CartDetailDTO cartDetailDTO : cartDetailDTOS) {
                CartResponseDTO cartResponseDTO = new CartResponseDTO();
                cartResponseDTO.setId(customerDTO.getId());
                cartResponseDTO.setFullName(customerDTO.getFullName());
                cartResponseDTO.setPhone(customerDTO.getPhone());
                cartResponseDTO.setLocationRegionDTO(customerDTO.getLocationRegion());
                cartResponseDTO.setTitle(cartDetailDTO.getTitle());
                cartResponseDTO.setPrice(cartDetailDTO.getPrice());
                cartResponseDTO.setQuantity(cartDetailDTO.getQuantity());
                cartResponseDTO.setAmount(cartDetailDTO.getAmount());
                cartResponseDTOList.add(cartResponseDTO);
            }


            cartService.checkout(user, cart);

        }

        return new ResponseEntity<>(cartResponseDTOList ,HttpStatus.OK);
    }
}