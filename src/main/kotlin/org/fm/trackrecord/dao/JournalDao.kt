package org.fm.trackrecord.dao

import org.fm.trackrecord.entity.JournalEntry
import org.springframework.data.jpa.repository.JpaRepository

interface JournalDao : JpaRepository<JournalEntry, Long> {

    fun findAllByPortfolioId(portfolioId: Long): MutableList<JournalEntry>
}
