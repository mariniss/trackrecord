package org.fm.trackrecord.api

import org.fm.trackrecord.entity.Portfolio
import org.fm.trackrecord.entity.Position
import org.fm.trackrecord.entity.Security
import org.fm.trackrecord.service.PositionService
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

@WebMvcTest(PositionApi::class)
class PositionApiTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    private lateinit var positionService: PositionService

    @Test
    fun `findAll should return all positions`() {
        val portfolio = Portfolio(id = 1, name = "first", currency = "USD")
        val security = Security(id = 1, ticker = "AAPL", name = "Apple Inc.", currency = "USD")
        val position1 = Position(id = 1, security = security, quantity = BigDecimal(10), costBasis = BigDecimal(1500), openDate = LocalDate.now(), portfolio = portfolio)
        val position2 = Position(id = 2, security = security, quantity = BigDecimal(20), costBasis = BigDecimal(3000), openDate = LocalDate.now(), portfolio = portfolio)
        `when`(positionService.findAll()).thenReturn(mutableListOf(position1, position2))

        mockMvc.perform(get("/api/position/"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.size()").value(2))
            .andExpect(jsonPath("$[0].quantity").value(10))
            .andExpect(jsonPath("$[1].quantity").value(20))
    }
}