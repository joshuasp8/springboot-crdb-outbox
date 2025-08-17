package com.pps.demo.core.business

import com.pps.demo.core.business.models.Demo
import com.pps.demo.core.business.models.UpdateDemoParams
import com.pps.demo.core.data.DemoEntity
import com.pps.demo.core.data.DemoRepository
import com.pps.demo.core.exceptions.DemoNotFoundException
import com.pps.demo.mappers.toModel
import org.springframework.stereotype.Component

@Component
class DemoUpdater(private val demoRepository: DemoRepository) {
  fun updateDemo(updateDemoParams: UpdateDemoParams): Demo {
    val entity = demoRepository.findById(updateDemoParams.id)
      .orElseThrow { DemoNotFoundException("Can't find demo with id ${updateDemoParams.id}") }

    val updatedEntity = DemoEntity(entity.id, updateDemoParams.message)
    val savedEntity = demoRepository.save(updatedEntity)
    return savedEntity.toModel()
  }
}
