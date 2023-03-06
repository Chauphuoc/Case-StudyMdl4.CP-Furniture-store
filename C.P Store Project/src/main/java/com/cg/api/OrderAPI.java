//package com.cg.api;
//
//import com.cg.model.Customer;
//import com.cg.model.Order;
//import com.cg.model.OrderDetail;
//import com.cg.model.User;
//import com.cg.model.dto.CustomerDTO;
//import com.cg.model.dto.OrderDetailDTO;
//import com.cg.service.customer.ICustomerService;
//import com.cg.service.order.IOrderService;
//import com.cg.service.orderDetail.IOrderDetailService;
//import com.cg.service.user.IUserService;
//import com.cg.utils.AppUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/orders")
//public class OrderAPI {
//
//    @Autowired
//    AppUtils appUtil;
//
//    @Autowired
//    IUserService userService;
//
//    @Autowired
//    IOrderService orderService;
//
//    @Autowired
//    IOrderDetailService orderDetailService;
//
//    @Autowired
//    ICustomerService customerService;
//
//    @GetMapping
//    public ResponseEntity<?> getAllOrderDetail() {
//        String username = appUtil.getUsernamePrincipal();
//        Optional<User> userOptional = userService.findByUsername(username);
//        User user = userOptional.get();
//        Order order = orderService.findOrderByUser(user);
//        OrderDetail orderDetail = orderDetailService.findOrderDetailByOrder(order);
//        OrderDetailDTO orderDetailDTO = orderDetail.toOrderDetailDTO();
//
//        Customer customer = customerService.findCustomerByUser(user);
//        CustomerDTO customerDTO = customer.toCustomerDTO();
//
//
//
//    }
//}
