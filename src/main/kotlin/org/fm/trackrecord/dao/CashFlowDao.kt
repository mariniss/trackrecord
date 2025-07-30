package org.fm.trackrecord.dao

import org.fm.trackrecord.entity.CashFlow
import org.springframework.data.jpa.repository.JpaRepository

interface CashFlowDao : JpaRepository<CashFlow, Long>