package com.pps.demo.core.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OrderOutboxEventRepository : JpaRepository<OrderOutboxEventEntity, UUID>
