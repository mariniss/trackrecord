package org.fm.trackrecord.api

import org.fm.trackrecord.entity.JournalEntry
import org.fm.trackrecord.entity.Portfolio
import org.fm.trackrecord.service.JournalService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate

@WebMvcTest(JournalApi::class)
class JournalApiTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    private lateinit var journalService: JournalService

    @Test
    fun `findAll should return all journalEntries`() {
        val portfolio = Portfolio(id = 1, name = "first", currency = "USD")
        val journalEntry1 = JournalEntry(id = 1, entryDate = LocalDate.now(), title = "title 1", content = "content 1", portfolio = portfolio)
        val journalEntry2 = JournalEntry(id = 2, entryDate = LocalDate.now(), title = "title 2", content = "content 2", portfolio = portfolio)
        `when`(journalService.findAll()).thenReturn(mutableListOf(journalEntry1, journalEntry2))

        mockMvc.perform(get("/api/journalEntry/"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.size()").value(2))
            .andExpect(jsonPath("$[0].title").value("title 1"))
            .andExpect(jsonPath("$[1].title").value("title 2"))
    }
}