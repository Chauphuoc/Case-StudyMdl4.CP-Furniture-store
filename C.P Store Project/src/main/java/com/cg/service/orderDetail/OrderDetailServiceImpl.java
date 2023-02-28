package com.cg.service.orderDetail;

import com.cg.model.OrderDetail;
import com.cg.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class OrderDetailServiceImpl implements IOrderDetailService{

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    public List<OrderDetail> findALl() {
        return null;
    }

    @Override
    public Optional<OrderDetail> findById(Long id) {
        return Optional.empty();
    }


    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(OrderDetail orderDetail) {

    }


}