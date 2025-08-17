package com.pps.demo.api.exceptions

import com.pps.demo.core.exceptions.DemoNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ApiExceptionHandler {

  @ExceptionHandler(DemoNotFoundException::class)
  fun handleDemoNotFoundException(ex: DemoNotFoundException): ResponseEntity<String> {
    return ResponseEntity.notFound().build()
  }
}
