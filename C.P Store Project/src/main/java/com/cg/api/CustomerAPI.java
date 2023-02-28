package com.cg.api;


import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.repository.CustomerRepository;
import com.cg.repository.LocationRegionRepository;
import com.cg.service.customer.ICustomerService;
import com.cg.service.locationRegion.ILocationRegionService;
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
    @Autowired
    private ILocationRegionService locationRegionService;


    @GetMapping ("/info")
    public ResponseEntity<?> getInfoCustomer() {
        String username = appUtils.getUsernamePrincipal();
        Optional<User> userOptional = userService.findByUsername(username);

        User user = userOptional.get();

        Optional<Customer> customerOptional = customerService.findByUser(user);

        if (customerOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customerOptional.get().toCustomerDTO(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> doCreate(@Validated @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {

        new CustomerDTO().validate(customerDTO, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        String username = appUtils.getUsernamePrincipal();

        Optional<User> userOptional = userService.findByUsername(username);

        User user = userOptional.get();

        Optional<Customer> customerOptional = customerService.findByUser(user);
        if (customerOptional.isPresent()) {
            throw new EmailExistsException("Account is existed");
        }
        customerDTO.setId(null);
        customerDTO.getLocationRegion().setId(null);
        Customer customer = customerDTO.toCustomer();
        customer.setUser(user);
        customerService.save(customer);
        return new ResponseEntity<>(customer.toCustomerDTO(), HttpStatus.CREATED);
    }



//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    @DeleteMapping ("/{id}")
//    public ResponseEntity<?> doDelete(@PathVariable Long id) {
//        Optional<Customer> customerOptional = customerService.findById(id);
//        if (!customerOptional.isPresent()) {
//            throw new DataInputException("Customer is not found");
//        }
//        Customer customer = customerOptional.get();
//        customer.setDeleted(true);
//        customerService.save(customer);
//        CustomerDTO newCustomerDTO = customer.toCustomerDTO();
//        return new ResponseEntity<>(newCustomerDTO, HttpStatus.OK);
//    }

//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/{customerId}")
    public ResponseEntity<?> doUpdate(@PathVariable Long customerId,@Validated @RequestBody CustomerDTO customerDTO) {
        String username = appUtils.getUsernamePrincipal();
        Optional<User> userOptional = userService.findByUsername(username);
        User user = userOptional.get();


        Optional<Customer> customerOptional = customerService.findByUser(user);
        if (!customerOptional.isPresent()) {
            throw new DataInputException("Customer is not found");
        }

        Customer updatedCustomer = customerOptional.get();
        updatedCustomer.setFullName(customerDTO.getFullName());
        updatedCustomer.setPhone(customerDTO.getPhone());
        updatedCustomer.setLocationRegion(customerDTO.getLocationRegion().toLocationRegion());
        locationRegionService.save(customerDTO.getLocationRegion().toLocationRegion());
        updatedCustomer.setUser(user);
        customerService.save(updatedCustomer);

        CustomerDTO updatedCustomerDTO = updatedCustomer.toCustomerDTO();
        return new ResponseEntity<>(updatedCustomerDTO, HttpStatus.OK);
    }




}
