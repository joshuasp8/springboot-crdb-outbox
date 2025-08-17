package com.pps.demo.core.outbox.models

import com.pps.demo.core.kafka.models.OrderEvent
import java.time.Instant
import java.util.UUID

data class OrderEventEnvelope(
  val id: UUID,
  val createdAt: Instant,
  val eventType: String,
  val eventData: OrderEvent,
)
