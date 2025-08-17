package com.pps.demo.core.business

import com.pps.demo.core.business.models.Order
import com.pps.demo.core.data.OrderRepository
import com.pps.demo.core.exceptions.OrderNotFoundException
import com.pps.demo.mappers.toModel
import org.springframework.stereotype.Component
import java.util.*

@Component
class OrderRetriever(private val orderRepository: OrderRepository) {
  fun getOrder(demoId: UUID): Order {
    return orderRepository.findById(demoId)
      .orElseThrow { OrderNotFoundException("Can't find order with id $demoId") }
      .toModel()
  }
}
