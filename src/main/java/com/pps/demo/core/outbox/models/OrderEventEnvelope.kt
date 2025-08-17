package com.pps.demo.core.outbox.models

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.pps.demo.core.kafka.models.OrderEvent
import java.time.Instant
import java.util.UUID

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class OrderEventEnvelope(
  val id: UUID,
  val createdAt: Instant,
  val eventType: String,
  val eventData: OrderEvent,
)
