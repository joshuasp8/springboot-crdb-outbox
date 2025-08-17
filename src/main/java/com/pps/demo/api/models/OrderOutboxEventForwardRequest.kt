package com.pps.demo.api.models

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.pps.demo.core.kafka.models.OrderEvent
import java.time.Instant
import java.util.UUID

data class OrderOutboxEventForwardRequest (
  val payload: List<OrderOutboxEventEnvelopeDto>,
  val length: Int?
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class OrderOutboxEventEnvelopeDto (
  val id: UUID,
  val createdAt: Instant,
  val eventType: String,
  val eventData: OrderEvent,
)
