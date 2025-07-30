package org.fm.trackrecord.service

import org.fm.trackrecord.dao.JournalDao
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
class JournalServiceTest {

    @InjectMocks
    private lateinit var journalService: JournalService

    @Mock
    private lateinit var journalDao: JournalDao

    @Test
    fun `findAll should return all journalEntries`() {
        val portfolio = Portfolio(id = 1, name = "first", currency = "USD")
        val journalEntry1 = JournalEntry(id = 1, entryDate = LocalDate.now(), title = "title 1", content = "content 1", portfolio = portfolio)
        val journalEntry2 = JournalEntry(id = 2, entryDate = LocalDate.now(), title = "title 2", content = "content 2", portfolio = portfolio)
        `when`(journalDao.findAll()).thenReturn(mutableListOf(journalEntry1, journalEntry2))
        val journalEntries = journalService.findAll()
        assert(journalEntries.size == 2)
        assert(journalEntries.contains(journalEntry1))
        assert(journalEntries.contains(journalEntry2))
    }
}