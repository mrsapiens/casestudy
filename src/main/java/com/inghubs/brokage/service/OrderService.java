package com.inghubs.brokage.service;

import com.inghubs.brokage.domain.bo.OrderBo;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    OrderBo createOrder(OrderBo orderBo);

    List<OrderBo> listOrders(Long customerId, LocalDateTime startDate, LocalDateTime endDate);

    OrderBo deleteOrder(Long orderId);
}
