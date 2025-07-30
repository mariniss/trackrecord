package org.fm.trackrecord.api

import org.fm.trackrecord.entity.CashFlow
import org.fm.trackrecord.entity.CashFlowType
import org.fm.trackrecord.entity.Portfolio
import org.fm.trackrecord.service.CashFlowService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.math.BigDecimal
import java.time.LocalDate

@WebMvcTest(CashFlowApi::class)
class CashFlowApiTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    private lateinit var cashFlowService: CashFlowService

    @Test
    fun `findAll should return all cashFlows`() {
        val portfolio = Portfolio(id = 1, name = "first", currency = "USD")
        val cashFlow1 = CashFlow(id = 1, date = LocalDate.now(), amount = BigDecimal(1000), type = CashFlowType.INFLOW, portfolio = portfolio)
        val cashFlow2 = CashFlow(id = 2, date = LocalDate.now(), amount = BigDecimal(2000), type = CashFlowType.OUTFLOW, portfolio = portfolio)
        `when`(cashFlowService.findAll()).thenReturn(mutableListOf(cashFlow1, cashFlow2))

        mockMvc.perform(get("/api/cash-flow/"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.size()").value(2))
            .andExpect(jsonPath("$[0].amount").value(1000))
            .andExpect(jsonPath("$[1].amount").value(2000))
    }
}