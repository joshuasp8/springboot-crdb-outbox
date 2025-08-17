package com.pps.demo.core.outbox

import com.pps.demo.core.kafka.DemoEventProducer
import com.pps.demo.core.outbox.models.OrderEventEnvelope
import org.springframework.stereotype.Component

@Component
class OrderOutboxForwarder(private val producer: DemoEventProducer) {

  fun forward(envelope: OrderEventEnvelope) {
    println("Forwarding event: $envelope")
    producer.send(envelope.eventData)
  }
}
