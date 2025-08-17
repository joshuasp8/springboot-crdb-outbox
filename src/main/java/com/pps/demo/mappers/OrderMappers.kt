package com.pps.demo.mappers

import com.pps.demo.api.models.CreateOrderRequest
import com.pps.demo.api.models.OrderDto
import com.pps.demo.api.models.UpdateOrderRequest
import com.pps.demo.core.business.models.CreateOrderParams
import com.pps.demo.core.business.models.Order
import com.pps.demo.core.business.models.UpdateOrderParams
import com.pps.demo.core.data.OrderEntity
import java.util.UUID

fun Order.toDto() = OrderDto(id = id.toString(), message = message)
fun OrderEntity.toModel() = Order(id = id, message = message)

fun map(request: CreateOrderRequest) = CreateOrderParams(message = request.message)
fun map(id: UUID, request: UpdateOrderRequest) = UpdateOrderParams(id = id, message = request.message)
