package org.fm.trackrecord.api

import org.fm.trackrecord.entity.Portfolio
import org.fm.trackrecord.service.PortfolioService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(PortfolioApi::class)
class PortfolioApiTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    private lateinit var portfolioService: PortfolioService

    @Test
    fun `findAll should return all portfolios`() {
        val portfolio1 = Portfolio(id = 1, name = "first", currency = "USD")
        val portfolio2 = Portfolio(id = 2, name = "second", currency = "USD")
        `when`(portfolioService.findAll()).thenReturn(mutableListOf(portfolio1, portfolio2))

        mockMvc.perform(get("/api/portfolio/"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.size()").value(2))
            .andExpect(jsonPath("$[0].name").value("first"))
            .andExpect(jsonPath("$[1].name").value("second"))
    }
}