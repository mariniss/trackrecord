package org.fm.trackrecord.service

import org.fm.trackrecord.dao.CashFlowDao
import org.fm.trackrecord.entity.CashFlow
import org.springframework.stereotype.Service

@Service
class CashFlowService(private val cashFlowDao: CashFlowDao) {

    fun findAll(): MutableList<CashFlow> = cashFlowDao.findAll()
}