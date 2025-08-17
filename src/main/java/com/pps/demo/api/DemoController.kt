package com.pps.demo.api

import com.pps.demo.api.models.CreateDemoRequest
import com.pps.demo.api.models.DemoDto
import com.pps.demo.api.models.UpdateDemoRequest
import com.pps.demo.core.business.DemoCreator
import com.pps.demo.core.business.DemoDeleter
import com.pps.demo.core.business.DemoRetriever
import com.pps.demo.core.business.DemoUpdater
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
@RequestMapping("/api/v1/demos")
class DemoController(
  private val demoRetriever: DemoRetriever,
  private val demoCreator: DemoCreator,
  private val demoUpdater: DemoUpdater,
  private val demoDeleter: DemoDeleter,
) {

  @GetMapping("/{demoId}")
  fun retrieveDemo(@PathVariable("demoId") demoId: UUID): ResponseEntity<DemoDto> {
    val demo = demoRetriever.getDemo(demoId)
    return ResponseEntity.ok(demo.toDto())
  }

  @PostMapping
  fun createDemo(@RequestBody request: CreateDemoRequest): ResponseEntity<DemoDto> {
    val demo = demoCreator.createDemo(map(request))
    return ResponseEntity.ok(demo.toDto())
  }

  @PutMapping("/{demoId}")
  fun updateDemo(@PathVariable("demoId") demoId: UUID, @RequestBody request: UpdateDemoRequest): ResponseEntity<DemoDto> {
    val demo = demoUpdater.updateDemo(map(demoId, request))
    return ResponseEntity.ok(demo.toDto())
  }

  @DeleteMapping("/{demoId}")
  fun deleteDemo(@PathVariable("demoId") demoId: UUID) {
    demoDeleter.deleteDemo(demoId)
  }
}
