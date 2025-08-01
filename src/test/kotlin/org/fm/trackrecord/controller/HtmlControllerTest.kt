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

@WebMvcTest(IndexController::class)
class HtmlControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    private lateinit var portfolioService: PortfolioService

    @Test
    fun `When GET then return index`() {
        val portfolio1 = Portfolio(id = 1, name = "first", currency = "USD")
        val portfolio2 = Portfolio(id = 2, name = "second", currency = "USD")
        `when`(portfolioService.findAll()).thenReturn(mutableListOf(portfolio1, portfolio2))

        mockMvc.perform(get("/"))
            .andExpect(status().isOk)
            .andExpect(view().name("index"))
//            .andExpect(model().attributeExists("portfolios"))
//            .andExpect(model().attribute("portfolios", portfolioService.findAll()))
    }
}