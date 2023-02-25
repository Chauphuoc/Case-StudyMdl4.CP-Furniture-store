package com.cg.api;


import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.repository.CustomerRepository;
import com.cg.service.customer.ICustomerService;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IUserService userService;

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private CustomerRepository customerRepository;


    @PostMapping
    public ResponseEntity<?> getInforCustomer() {
        String username = appUtils.getUsernamePrincipal();
        Optional<User> userOptional = userService.findByUsername(username);
        User user = userOptional.get();
        return new ResponseEntity<>(user.toUserDTO(), HttpStatus.OK);
    }




//    @PostMapping
//    public ResponseEntity<?> doCreate(@Validated @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {
//
//        new CustomerDTO().validate(customerDTO, bindingResult);
//
//        if (bindingResult.hasFieldErrors()) {
//            return appUtils.mapErrorToResponse(bindingResult);
//        }
//
//        Optional<Customer> customerOptional = customerService.findByEmail(customerDTO.getEmail());
//        if (customerOptional.isPresent()) {
//            throw new EmailExistsException("Email is existed");
//        }
//        customerDTO.setId(null);
//        customerDTO.setBalance(BigDecimal.ZERO);
//        customerDTO.getLocationRegion().setId(null);
//        Customer customer = customerDTO.toCustomer();
//        customerService.save(customer);
//        return new ResponseEntity<>(customer.toCustomerDTO(), HttpStatus.CREATED);
//    }



    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> doDelete(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()) {
            throw new DataInputException("Customer is not found");
        }
        Customer customer = customerOptional.get();
        customer.setDeleted(true);
        customerService.save(customer);
        CustomerDTO newCustomerDTO = customer.toCustomerDTO();
        return new ResponseEntity<>(newCustomerDTO, HttpStatus.OK);
    }
//
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    @PatchMapping("/{customerId}")
//    public ResponseEntity<?> doUpdate(@PathVariable Long customerId,@Validated @RequestBody CustomerDTO customerDTO) {
//        Optional<Customer> customerOptional = customerService.findById(customerId);
//        if (!customerOptional.isPresent()) {
//            throw new DataInputException("Customer is not found");
//        }
//        Customer updatedCustomer = customerOptional.get();
//        updatedCustomer.setFullName(customerDTO.getFullName());
//        updatedCustomer.setEmail(customerDTO.getEmail());
//        updatedCustomer.setPhone(customerDTO.getPhone());
//        updatedCustomer.setLocationRegion(customerDTO.getLocationRegion().toLocationRegion());
//
//        customerService.save(updatedCustomer);
//        CustomerDTO updatedCustomerDTO = updatedCustomer.toCustomerDTO();
//        return new ResponseEntity<>(updatedCustomerDTO, HttpStatus.OK);
//    }




}
