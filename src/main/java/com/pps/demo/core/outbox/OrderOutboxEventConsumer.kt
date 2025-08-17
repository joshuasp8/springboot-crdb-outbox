package com.pps.demo.core.outbox

import com.pps.demo.core.outbox.models.OrderEventEnvelope
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class OrderOutboxEventConsumer(val forwarder: OrderOutboxForwarder) {
  @KafkaListener(topics = ["order-outbox-events"], containerFactory = "orderOutboxEventKafkaListenerContainerFactory")
  fun consume(event: OrderEventEnvelope, ack: Acknowledgment) {
    println("Outbox message received: $event")
    forwarder.forward(event)
    ack.acknowledge()
  }
}
