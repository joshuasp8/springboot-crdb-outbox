package com.pps.demo.api

import com.pps.demo.core.outbox.OrderOutboxForwarder
import com.pps.demo.core.outbox.models.OrderEventEnvelope
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/outbox-events")
class OutboxEventController(
  private val forwarder: OrderOutboxForwarder
) {
  // TODO: fix - this needs its own DTO
  @PostMapping("/forward")
  fun forwardOutboxEvents(@RequestBody orderEventEnvelope: OrderEventEnvelope) {
    forwarder.forward(orderEventEnvelope)
  }
}
