package com.pps.demo.core.kafka

import com.pps.demo.core.kafka.models.DemoEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class DemoEventProducer(private val demoEventKafkaTemplate: KafkaTemplate<String, DemoEvent>) {
  fun send(event: DemoEvent) {
    demoEventKafkaTemplate.send("demo-order-topic", event)
  }
}
