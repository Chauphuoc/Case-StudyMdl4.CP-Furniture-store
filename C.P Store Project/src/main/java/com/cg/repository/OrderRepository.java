package com.cg.repository;

import com.cg.model.Order;
import com.cg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderByUser(User user);
}