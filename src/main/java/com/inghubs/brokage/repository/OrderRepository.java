package com.inghubs.brokage.repository;

import com.inghubs.brokage.domain.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByIdAndCreateDateBetween(Long customerId, LocalDateTime startDate, LocalDateTime endDate);
}
