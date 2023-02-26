package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.User;
import com.cg.model.dto.CustomerDTO;
import com.cg.service.IGeneralService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {

    Optional<Customer> findByUser(User user);

    List<CustomerDTO> findALlCustomerDTO();

    List<Customer> findAllByDeletedIsFalse();

    List<Customer> findAllByIdNot(Long id);

    List<Customer> findAllByIdNotAndDeletedIsFalse(Long id);

}
