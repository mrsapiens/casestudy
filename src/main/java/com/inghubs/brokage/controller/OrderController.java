package com.inghubs.brokage.controller;

import com.inghubs.brokage.domain.bo.OrderBo;
import com.inghubs.brokage.domain.mapper.OrderMapper;
import com.inghubs.brokage.domain.model.Order;
import com.inghubs.brokage.service.OrderService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        OrderBo orderBo = orderService.createOrder(orderMapper.convert(order));

        return ResponseEntity.ok(orderMapper.convert(orderBo));
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Order> deleteOrder(@NotNull @PathVariable("orderId") Long orderId) {
        OrderBo orderBo = orderService.deleteOrder(orderId);

        return ResponseEntity.ok(orderMapper.convert(orderBo));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Order>> listOrders(@NotNull @RequestParam Long customerId,
                                                  @NotNull @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateStart,
                                                  @NotNull @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateEnd) {
        List<OrderBo> orderBos = orderService.listOrders(customerId, dateStart, dateEnd);

        return ResponseEntity.ok(orderBos.stream().map(orderMapper::convert).toList());
    }
}
