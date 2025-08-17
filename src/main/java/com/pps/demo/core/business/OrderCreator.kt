package com.pps.demo.core.business

import com.fasterxml.jackson.databind.ObjectMapper
import com.pps.demo.core.business.models.CreateOrderParams
import com.pps.demo.core.business.models.Order
import com.pps.demo.core.data.OrderEntity
import com.pps.demo.core.data.OrderOutboxEventEntity
import com.pps.demo.core.data.OrderOutboxEventRepository
import com.pps.demo.core.data.OrderRepository
import com.pps.demo.core.kafka.models.OrderEvent
import com.pps.demo.mappers.toModel
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.util.UUID

@Component
class OrderCreator(
  private val orderRepository: OrderRepository,
  private val orderOutboxEventRepository: OrderOutboxEventRepository,
  private val objectMapper: ObjectMapper
) {

  @Transactional
  fun createOrder(createOrderParams: CreateOrderParams): Order {
    val now = Instant.now()
    val entity = OrderEntity(
      id = UUID.randomUUID(),
      createdAt = now,
      updatedAt = now,
      message = createOrderParams.message
    )
    val savedEntity = orderRepository.save(entity)

    val outboxEventEntity = buildOutboxEntity(entity.id, entity.message, now)
    orderOutboxEventRepository.save(outboxEventEntity)

    return savedEntity.toModel()
  }

  private fun buildOutboxEntity(
    orderId: UUID,
    message: String,
    eventTime: Instant
  ): OrderOutboxEventEntity {
    val orderEvent = OrderEvent(
      id = UUID.randomUUID().toString(),
      orderId = orderId.toString(),
      message = message
    )
    val outboxEventEntity = OrderOutboxEventEntity(
      id = UUID.randomUUID(),
      createdAt = eventTime,
      eventType = "CREATED",
      eventData = objectMapper.writeValueAsString(orderEvent)
      )
    return outboxEventEntity
  }
}
