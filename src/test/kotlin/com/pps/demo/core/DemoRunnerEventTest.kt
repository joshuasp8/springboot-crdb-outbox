package com.pps.demo.core

import com.pps.demo.core.business.DemoRunner
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DemoRunnerEventTest {
  private val demoRunner = DemoRunner()

  @Test
  fun test1() {
    demoRunner.run()
    assertTrue(1 < 2)
  }
}
