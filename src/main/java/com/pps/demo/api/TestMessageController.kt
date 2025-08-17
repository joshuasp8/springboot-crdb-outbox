package com.pps.demo.api

import com.pps.demo.api.models.SendMessageRequest
import com.pps.demo.core.kafka.DemoEventProducer
import com.pps.demo.core.kafka.models.DemoEvent
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/messages")
class TestMessageController(private val producer: DemoEventProducer) {
  @PostMapping("/send")
  fun sendMessage(@RequestBody request: SendMessageRequest): ResponseEntity<String> {
    val event = DemoEvent(id = UUID.randomUUID().toString(), message = request.message)
    producer.send(event)
    return ResponseEntity.ok("Message sent: $event")
  }
}
