package com.pps.demo.core.business

import com.pps.demo.core.business.models.CreateOrderParams
import com.pps.demo.core.business.models.UpdateOrderParams
import com.pps.demo.core.exceptions.OrderNotFoundException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import kotlin.test.assertEquals

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // use real database
@DataJpaTest
@Import(OrderCreator::class, OrderRetriever::class, OrderUpdater::class, OrderDeleter::class)
class OrderCrudIT @Autowired constructor(
  private val orderCreator: OrderCreator,
  private val orderRetriever: OrderRetriever,
  private val orderUpdater: OrderUpdater,
  private val orderDeleter: OrderDeleter
) {

  @Test
  fun `should create, retrieve, update, and delete a demo`() {
    // create demo
    val createdDemo = orderCreator.createOrder(CreateOrderParams("Hello World"))
    val retrievedDemo = orderRetriever.getOrder(createdDemo.id)
    assertEquals(retrievedDemo, createdDemo)

    // update demo
    orderUpdater.updateDemo(UpdateOrderParams(retrievedDemo.id, "Goodbye World"))
    val updatedDemo = orderRetriever.getOrder(retrievedDemo.id)
    assertEquals(updatedDemo, createdDemo)
    assertEquals(updatedDemo.message, "Goodbye World")

    // delete demo
    orderDeleter.deleteDemo(updatedDemo.id)
    // attempt to retrieve deleted demo and verify not-found exception
    assertThrows<OrderNotFoundException> { orderRetriever.getOrder(updatedDemo.id) }
  }
}
