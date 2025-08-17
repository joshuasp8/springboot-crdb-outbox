package com.pps.demo.core.kafka

import com.pps.demo.core.kafka.models.OrderEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class DemoEventProducer(private val orderEventKafkaTemplate: KafkaTemplate<String, OrderEvent>) {
  fun send(event: OrderEvent) {
    orderEventKafkaTemplate.send("demo-order-topic", event)
  }
}
