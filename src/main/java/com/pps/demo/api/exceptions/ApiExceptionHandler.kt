package com.pps.demo.api.exceptions

import com.pps.demo.core.exceptions.OrderNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ApiExceptionHandler {

  @ExceptionHandler(OrderNotFoundException::class)
  fun handleOrderNotFoundException(ex: OrderNotFoundException): ResponseEntity<String> {
    return ResponseEntity.notFound().build()
  }
}
