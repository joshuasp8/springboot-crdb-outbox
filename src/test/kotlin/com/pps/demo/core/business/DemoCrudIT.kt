package com.pps.demo.core.business

import com.pps.demo.core.business.models.CreateDemoParams
import com.pps.demo.core.business.models.UpdateDemoParams
import com.pps.demo.core.exceptions.DemoNotFoundException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import kotlin.test.assertEquals

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // use real database
@DataJpaTest
@Import(DemoCreator::class, DemoRetriever::class, DemoUpdater::class, DemoDeleter::class)
class DemoCrudIT @Autowired constructor(
  private val demoCreator: DemoCreator,
  private val demoRetriever: DemoRetriever,
  private val demoUpdater: DemoUpdater,
  private val demoDeleter: DemoDeleter
) {

  @Test
  fun `should create, retrieve, update, and delete a demo`() {
    // create demo
    val createdDemo = demoCreator.createDemo(CreateDemoParams("Hello World"))
    val retrievedDemo = demoRetriever.getDemo(createdDemo.id)
    assertEquals(retrievedDemo, createdDemo)

    // update demo
    demoUpdater.updateDemo(UpdateDemoParams(retrievedDemo.id, "Goodbye World"))
    val updatedDemo = demoRetriever.getDemo(retrievedDemo.id)
    assertEquals(updatedDemo, createdDemo)
    assertEquals(updatedDemo.message, "Goodbye World")

    // delete demo
    demoDeleter.deleteDemo(updatedDemo.id)
    // attempt to retrieve deleted demo and verify not-found exception
    assertThrows<DemoNotFoundException> { demoRetriever.getDemo(updatedDemo.id) }
  }
}
