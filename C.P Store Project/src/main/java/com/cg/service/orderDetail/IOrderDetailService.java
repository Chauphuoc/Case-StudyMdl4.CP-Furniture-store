package com.cg.service.orderDetail;

import com.cg.model.Order;
import com.cg.model.OrderDetail;
import com.cg.service.IGeneralService;

public interface IOrderDetailService extends IGeneralService<OrderDetail> {
    OrderDetail findOrderDetailByOrder(Order order);
}