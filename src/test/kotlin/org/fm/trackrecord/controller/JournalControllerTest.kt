package org.fm.trackrecord.controller

import org.fm.trackrecord.entity.JournalEntry
import org.fm.trackrecord.entity.Portfolio
import org.fm.trackrecord.service.JournalService
import org.fm.trackrecord.service.PortfolioService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate

@WebMvcTest(JournalController::class)
class JournalControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    private lateinit var portfolioService: PortfolioService

    @MockBean
    private lateinit var journalService: JournalService

    @Test
    fun `should return journal view with portfolio and journal entries`() {
        val portfolio = Portfolio(id = 1, name = "Test Portfolio", currency = "USD")
        val journalEntry = JournalEntry(id = 1, entryDate = LocalDate.now(), title = "Test Entry", content = "Test Content", portfolio = portfolio)
        `when`(journalService.findAllByPortfolioId(1)).thenReturn(mutableListOf(journalEntry))

        mockMvc.perform(get("/{portfolioId}/journal", 1L))
            .andExpect(status().isOk)
            .andExpect(view().name("journal"))
            .andExpect(model().attributeExists("portfolioId"))
            .andExpect(model().attribute("portfolioId", 1L))
            .andExpect(model().attributeExists("journalEntries"))
            .andExpect(model().attribute("journalEntries", listOf(journalEntry)))
    }

    @Test
    fun `should show add journal entry form`() {
        mockMvc.perform(get("/{portfolioId}/journal/add", 1L))
            .andExpect(status().isOk)
            .andExpect(view().name("add-journal-entry"))
            .andExpect(model().attributeExists("portfolioId"))
            .andExpect(model().attribute("portfolioId", 1L))
            .andExpect(model().attributeExists("journalEntry"))
    }

    @Test
    fun `should add journal entry`() {
        mockMvc.perform(
            post("/{portfolioId}/journal/add", 1L)
                .param("entryDate", "2025-01-01")
                .param("title", "New Entry")
                .param("content", "Test Content")
        )
            .andExpect(status().is3xxRedirection)
            .andExpect(redirectedUrl("/1/journal"))
    }
}
