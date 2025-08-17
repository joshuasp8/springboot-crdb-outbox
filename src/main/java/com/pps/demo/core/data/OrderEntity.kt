package com.pps.demo.core.data

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "orders")
class OrderEntity(
  @Id
  val id: UUID,
  val createdAt: Instant,
  var updatedAt: Instant,
  var message: String,
)
