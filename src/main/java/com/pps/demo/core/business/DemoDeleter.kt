package com.pps.demo.core.business

import com.pps.demo.core.data.DemoRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class DemoDeleter(private val demoRepository: DemoRepository) {
  fun deleteDemo(demoId: UUID) {
    demoRepository.deleteById(demoId)
  }
}
