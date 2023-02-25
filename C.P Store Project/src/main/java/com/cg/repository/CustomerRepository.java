package com.cg.repository;

import com.cg.model.Customer;
import com.cg.model.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
// tham chiếu đến bảng customer
    @Query("SELECT NEW com.cg.model.dto.CustomerDTO (" +
                "c.id, " +
                "c.fullName, " +
                "c.email, " +
                "c.phone, " +
                "c.locationRegion" +
            ") " +
            "FROM Customer AS c " +
            "WHERE c.deleted = false "
    )



    List<CustomerDTO> findAllCustomerDTO();

    List<Customer> findAllByDeletedIsFalse();




    List<Customer> findAllByIdNot(Long id);

    List<Customer> findAllByIdNotAndDeletedIsFalse(Long id);

    Optional<Customer> findByEmail(String email);


}
