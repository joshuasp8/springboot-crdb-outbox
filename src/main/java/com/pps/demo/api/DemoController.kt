package com.pps.demo.api

import com.pps.demo.api.models.CreateOrderRequest
import com.pps.demo.api.models.OrderDto
import com.pps.demo.api.models.UpdateOrderRequest
import com.pps.demo.core.business.OrderCreator
import com.pps.demo.core.business.OrderDeleter
import com.pps.demo.core.business.OrderRetriever
import com.pps.demo.core.business.OrderUpdater
import com.pps.demo.mappers.map
import com.pps.demo.mappers.toDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/orders")
class DemoController(
  private val orderRetriever: OrderRetriever,
  private val orderCreator: OrderCreator,
  private val orderUpdater: OrderUpdater,
  private val orderDeleter: OrderDeleter,
) {

  @GetMapping("/{orderId}")
  fun retrieveDemo(@PathVariable("orderId") orderId: UUID): ResponseEntity<OrderDto> {
    val order = orderRetriever.getOrder(orderId)
    return ResponseEntity.ok(order.toDto())
  }

  @PostMapping
  fun createDemo(@RequestBody request: CreateOrderRequest): ResponseEntity<OrderDto> {
    val order = orderCreator.createOrder(map(request))
    return ResponseEntity.ok(order.toDto())
  }

  @PutMapping("/{orderId}")
  fun updateDemo(@PathVariable("orderId") orderId: UUID, @RequestBody request: UpdateOrderRequest): ResponseEntity<OrderDto> {
    val order = orderUpdater.updateDemo(map(orderId, request))
    return ResponseEntity.ok(order.toDto())
  }

  @DeleteMapping("/{orderId}")
  fun deleteDemo(@PathVariable("orderId") orderId: UUID) {
    orderDeleter.deleteDemo(orderId)
  }
}
