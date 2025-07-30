package org.fm.trackrecord.api

import org.fm.trackrecord.entity.Action
import org.fm.trackrecord.entity.ActionType
import org.fm.trackrecord.entity.JournalEntry
import org.fm.trackrecord.entity.Portfolio
import org.fm.trackrecord.service.ActionService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate

@WebMvcTest(ActionApi::class)
class ActionApiTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    private lateinit var actionService: ActionService

    @Test
    fun `findAll should return all actions`() {
        val portfolio = Portfolio(id = 1, name = "first", currency = "USD")
        val journalEntry = JournalEntry(id = 1, entryDate = LocalDate.now(), title = "title 1", content = "content 1", portfolio = portfolio)
        val action1 = Action(id = 1, date = LocalDate.now(), type = ActionType.BUY, journalEntry = journalEntry)
        val action2 = Action(id = 2, date = LocalDate.now(), type = ActionType.SELL, journalEntry = journalEntry)
        `when`(actionService.findAll()).thenReturn(mutableListOf(action1, action2))

        mockMvc.perform(get("/api/action/"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.size()").value(2))
            .andExpect(jsonPath("$[0].type").value("BUY"))
            .andExpect(jsonPath("$[1].type").value("SELL"))
    }
}