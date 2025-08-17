package com.pps.demo.core.business

import com.pps.demo.core.data.OrderRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class OrderDeleter(private val orderRepository: OrderRepository) {
  fun deleteDemo(orderId: UUID) {
    orderRepository.deleteById(orderId)
  }
}
