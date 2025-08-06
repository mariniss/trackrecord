package org.fm.trackrecord.service

import org.fm.trackrecord.dao.JournalDao
import org.fm.trackrecord.entity.JournalEntry
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class JournalService(private val journalDao: JournalDao, private val portfolioService: PortfolioService) {

    fun findAll(): MutableList<JournalEntry> = journalDao.findAll()

    fun findAllByPortfolioId(portfolioId: Long) : MutableList<JournalEntry> = journalDao.findAllByPortfolioId(portfolioId)

    fun addNew(journalEntry: JournalEntry, portfolioId: Long) {
        val portfolio = portfolioService.findById(portfolioId)
            .orElseThrow { IllegalStateException("Portfolio not found") }

        journalEntry.portfolio = portfolio
        journalDao.save(journalEntry)
    }
}
