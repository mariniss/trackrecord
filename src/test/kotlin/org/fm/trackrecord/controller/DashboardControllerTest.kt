package org.fm.trackrecord.controller

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
import java.util.Optional

@WebMvcTest(DashboardController::class)
class DashboardControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    private lateinit var portfolioService: PortfolioService

    @Test
    fun `should return dashboard view with portfolio data`() {
        val portfolio = Portfolio(id = 1, name = "Test Portfolio", currency = "USD")
        `when`(portfolioService.findById(1)).thenReturn(Optional.of(portfolio))

        mockMvc.perform(get("/{portfolioId}/dashboard", 1L))
            .andExpect(status().isOk)
            .andExpect(view().name("dashboard"))
            .andExpect(model().attributeExists("portfolio"))
            .andExpect(model().attribute("portfolio", portfolio))
    }
}
