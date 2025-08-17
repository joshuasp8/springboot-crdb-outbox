package com.pps.demo.core.data

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID


@Entity
@Table(name = "order_outbox_events")
class OrderOutboxEventEntity(
  @Id
  val id: UUID,
  val createdAt: Instant,
  var eventType: String,
  var eventData: String,
)
