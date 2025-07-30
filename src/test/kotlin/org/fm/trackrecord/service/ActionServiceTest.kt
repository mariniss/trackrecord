package org.fm.trackrecord.service

import org.fm.trackrecord.dao.ActionDao
import org.fm.trackrecord.entity.Action
import org.fm.trackrecord.entity.ActionType
import org.fm.trackrecord.entity.JournalEntry
import org.fm.trackrecord.entity.Portfolio
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class ActionServiceTest {

    @InjectMocks
    private lateinit var actionService: ActionService

    @Mock
    private lateinit var actionDao: ActionDao

    @Test
    fun `findAll should return all actions`() {
        val portfolio = Portfolio(id = 1, name = "first", currency = "USD")
        val journalEntry = JournalEntry(id = 1, entryDate = LocalDate.now(), title = "title 1", content = "content 1", portfolio = portfolio)
        val action1 = Action(id = 1, date = LocalDate.now(), type = ActionType.BUY, journalEntry = journalEntry)
        val action2 = Action(id = 2, date = LocalDate.now(), type = ActionType.SELL, journalEntry = journalEntry)
        `when`(actionDao.findAll()).thenReturn(mutableListOf(action1, action2))
        val actions = actionService.findAll()
        assert(actions.size == 2)
        assert(actions.contains(action1))
        assert(actions.contains(action2))
    }
}