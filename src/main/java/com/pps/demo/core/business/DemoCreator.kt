package com.pps.demo.core.business

import com.pps.demo.core.business.models.CreateDemoParams
import com.pps.demo.core.business.models.Demo
import com.pps.demo.core.data.DemoEntity
import com.pps.demo.core.data.DemoRepository
import com.pps.demo.mappers.toModel
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class DemoCreator(private val demoRepository: DemoRepository) {
  fun createDemo(createDemoParams: CreateDemoParams): Demo {
    val entity = DemoEntity(UUID.randomUUID(), createDemoParams.message)
    val savedEntity = demoRepository.save(entity)
    return savedEntity.toModel()
  }
}
