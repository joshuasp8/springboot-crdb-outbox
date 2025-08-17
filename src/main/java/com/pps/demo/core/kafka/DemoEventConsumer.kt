package com.pps.demo.core.kafka

import com.pps.demo.core.kafka.models.OrderEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class DemoEventConsumer {
  @KafkaListener(topics = ["demo-order-topic"], containerFactory = "demoEventKafkaListenerContainerFactory")
  fun consume(event: OrderEvent, ack: Acknowledgment) {
    println("Message received: ${event.message}")
    ack.acknowledge()
  }
}
