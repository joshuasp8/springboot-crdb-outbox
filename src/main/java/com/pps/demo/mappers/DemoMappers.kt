package com.pps.demo.mappers

import com.pps.demo.api.models.CreateDemoRequest
import com.pps.demo.api.models.DemoDto
import com.pps.demo.api.models.UpdateDemoRequest
import com.pps.demo.core.business.models.CreateDemoParams
import com.pps.demo.core.business.models.Demo
import com.pps.demo.core.business.models.UpdateDemoParams
import com.pps.demo.core.data.DemoEntity
import java.util.UUID

fun Demo.toDto() = DemoDto(id = id.toString(), message = message)
fun DemoEntity.toModel() = Demo(id = id, message = message)

fun map(request: CreateDemoRequest) = CreateDemoParams(message = request.message)
fun map(id: UUID, request: UpdateDemoRequest) =
  UpdateDemoParams(id = id, message = request.message)
