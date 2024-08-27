package com.inghubs.brokage.service.impl;

import com.inghubs.brokage.domain.bo.CustomerBo;
import com.inghubs.brokage.domain.bo.OrderBo;
import com.inghubs.brokage.domain.entity.CustomerEntity;
import com.inghubs.brokage.domain.entity.OrderEntity;
import com.inghubs.brokage.domain.enumeration.OrderStatus;
import com.inghubs.brokage.domain.exception.AssetNotFoundException;
import com.inghubs.brokage.domain.exception.CustomerNotFoundException;
import com.inghubs.brokage.domain.exception.OrderNotFoundException;
import com.inghubs.brokage.domain.mapper.CustomerEntityMapper;
import com.inghubs.brokage.domain.mapper.OrderEntityMapper;
import com.inghubs.brokage.repository.AssetRepository;
import com.inghubs.brokage.repository.CustomerRepository;
import com.inghubs.brokage.repository.OrderRepository;
import com.inghubs.brokage.service.OrderService;
import com.inghubs.brokage.validator.OrderValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderMapper;
    private final OrderValidator orderValidator;
    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper customerMapper;
    private final AssetRepository assetRepository;

    @Override
    public OrderBo createOrder(OrderBo orderBo) {
        CustomerEntity customer = customerRepository.findById(orderBo.getCustomerId()).orElseThrow(CustomerNotFoundException::new);

        assetRepository.findByAssetName(orderBo.getAssetName()).orElseThrow(AssetNotFoundException::new);

        CustomerBo customerBo = customerMapper.convert(customer);

        orderValidator.validateOrder(orderBo, customerBo);

        initialize(orderBo);

        OrderEntity orderEntity = orderMapper.convert(orderBo);
        orderEntity.setCustomer(customer);

        return persistOrder(orderEntity);
    }

    @Override
    public List<OrderBo> listOrders(Long customerId, LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByIdAndCreateDateBetween(customerId, startDate, endDate)
                .stream()
                .map(orderMapper::convert)
                .filter(o -> !o.getStatus().equals(OrderStatus.CANCELLED))
                .toList();
    }

    @Override
    public OrderBo deleteOrder(Long orderId) {
        OrderBo orderBo = retrieveOrder(orderId).orElseThrow(OrderNotFoundException::new);

        CustomerEntity customer = customerRepository.findById(orderBo.getCustomerId()).orElseThrow(CustomerNotFoundException::new);

        orderBo.setStatus(OrderStatus.CANCELLED);

        OrderEntity orderEntity = orderMapper.convert(orderBo);
        orderEntity.setCustomer(customer);

        return persistOrder(orderEntity);
    }

    private Optional<OrderBo> retrieveOrder(Long orderId) {
        return orderRepository.findById(orderId).map(orderMapper::convert);
    }

    private OrderBo persistOrder(OrderEntity orderEntity) {
        return orderMapper.convert(orderRepository.save(orderEntity));
    }

    private OrderBo persistOrder(OrderBo orderBo) {
        OrderEntity order = orderMapper.convert(orderBo);
        return orderMapper.convert(orderRepository.save(order));
    }

    private void initialize(OrderBo orderBo) {
        orderBo.setStatus(OrderStatus.PENDING);
        orderBo.setCreateDate(LocalDateTime.now());
    }
}
