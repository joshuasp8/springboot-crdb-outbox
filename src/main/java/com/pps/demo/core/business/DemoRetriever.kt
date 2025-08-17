package com.pps.demo.core.business

import com.pps.demo.core.business.models.Demo
import com.pps.demo.core.data.DemoRepository
import com.pps.demo.core.exceptions.DemoNotFoundException
import com.pps.demo.mappers.toModel
import org.springframework.stereotype.Component
import java.util.*

@Component
class DemoRetriever(private val demoRepository: DemoRepository) {
  fun getDemo(demoId: UUID): Demo {
    return demoRepository.findById(demoId)
      .orElseThrow { DemoNotFoundException("Can't find demo with id $demoId") }
      .toModel()
  }
}
