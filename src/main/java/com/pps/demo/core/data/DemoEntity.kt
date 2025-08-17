package com.pps.demo.core.data

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "demos")
class DemoEntity(
  @Id
  val id: UUID,

  var message: String,
)
