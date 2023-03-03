package com.cg.service.order;

import com.cg.model.Order;
import com.cg.model.User;
import com.cg.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<Order> findALl() {
        return null;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }


    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public Order findOrderByUser(User user) {
        return orderRepository.findOrderByUser(user);
    }
}