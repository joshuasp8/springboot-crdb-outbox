package com.pps.demo.core.business

import com.pps.demo.core.business.models.CreateOrderParams
import com.pps.demo.core.business.models.Order
import com.pps.demo.core.data.OrderEntity
import com.pps.demo.core.data.OrderRepository
import com.pps.demo.mappers.toModel
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.UUID

@Component
class OrderCreator(private val orderRepository: OrderRepository) {
  fun createOrder(createOrderParams: CreateOrderParams): Order {
    val now = Instant.now()
    val entity = OrderEntity(
      id = UUID.randomUUID(),
      createdAt = now,
      updatedAt = now,
      message = createOrderParams.message
    )
    val savedEntity = orderRepository.save(entity)
    return savedEntity.toModel()
  }
}
