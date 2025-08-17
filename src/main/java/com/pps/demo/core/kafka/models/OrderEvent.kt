package com.pps.demo.core.kafka.models

data class OrderEvent(val id: String, val orderId: String, val message: String)
