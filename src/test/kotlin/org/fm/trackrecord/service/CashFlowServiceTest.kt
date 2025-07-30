package org.fm.trackrecord.service

import org.fm.trackrecord.dao.CashFlowDao
import org.fm.trackrecord.entity.CashFlow
import org.fm.trackrecord.entity.CashFlowType
import org.fm.trackrecord.entity.Portfolio
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class CashFlowServiceTest {

    @InjectMocks
    private lateinit var cashFlowService: CashFlowService

    @Mock
    private lateinit var cashFlowDao: CashFlowDao

    @Test
    fun `findAll should return all cashFlows`() {
        val portfolio = Portfolio(id = 1, name = "first", currency = "USD")
        val cashFlow1 = CashFlow(id = 1, date = LocalDate.now(), amount = BigDecimal(1000), type = CashFlowType.INFLOW, portfolio = portfolio)
        val cashFlow2 = CashFlow(id = 2, date = LocalDate.now(), amount = BigDecimal(2000), type = CashFlowType.OUTFLOW, portfolio = portfolio)
        `when`(cashFlowDao.findAll()).thenReturn(mutableListOf(cashFlow1, cashFlow2))
        val cashFlows = cashFlowService.findAll()
        assert(cashFlows.size == 2)
        assert(cashFlows.contains(cashFlow1))
        assert(cashFlows.contains(cashFlow2))
    }
}