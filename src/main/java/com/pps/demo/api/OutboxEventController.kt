package com.pps.demo.api

import com.pps.demo.api.models.OrderOutboxEventEnvelopeDto
import com.pps.demo.api.models.OrderOutboxEventForwardRequest
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
  @PostMapping("/forward")
  fun forwardOutboxEvents(@RequestBody forwardRequest: OrderOutboxEventForwardRequest) {
    println("Received Forwarding request: $forwardRequest")
    forwardRequest.payload.forEach { envelopeDto ->
      forwarder.forward(envelopeDto.toOrderEventEnvelope())
    }
  }

  private fun OrderOutboxEventEnvelopeDto.toOrderEventEnvelope(): OrderEventEnvelope {
    return OrderEventEnvelope(
      id = this.id,
      createdAt = this.createdAt,
      eventType = this.eventType,
      eventData = this.eventData
    )
  }
}
