package com.pps.demo.core.business

import com.pps.demo.core.business.models.Order
import com.pps.demo.core.business.models.UpdateOrderParams
import com.pps.demo.core.data.OrderEntity
import com.pps.demo.core.data.OrderRepository
import com.pps.demo.core.exceptions.OrderNotFoundException
import com.pps.demo.mappers.toModel
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class OrderUpdater(private val orderRepository: OrderRepository) {
  fun updateDemo(updateOrderParams: UpdateOrderParams): Order {
    val entity = orderRepository.findById(updateOrderParams.id)
      .orElseThrow { OrderNotFoundException("Can't find order with id ${updateOrderParams.id}") }

    val updatedEntity = OrderEntity(
      id = entity.id,
      createdAt = entity.createdAt,
      updatedAt = Instant.now(),
      message = updateOrderParams.message
    )
    val savedEntity = orderRepository.save(updatedEntity)
    return savedEntity.toModel()
  }
}
