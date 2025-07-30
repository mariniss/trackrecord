package org.fm.trackrecord.dao

import org.assertj.core.api.Assertions.assertThat
import org.fm.trackrecord.entity.JournalEntry
import org.fm.trackrecord.entity.Portfolio
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.LocalDate

@DataJpaTest
class JournalDaoTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val journalDao: JournalDao,
    val portfolioDao: PortfolioDao
) {

    @Test
    fun `When findById then return JournalEntry`() {
        val portfolio = Portfolio(name = "test", currency = "USD")
        entityManager.persist(portfolio)
        val journalEntry = JournalEntry(
            entryDate = LocalDate.now(),
            title = "test title",
            content = "test content",
            portfolio = portfolio
        )
        entityManager.persist(journalEntry)
        entityManager.flush()
        val found = journalDao.findById(journalEntry.id!!)
        assertThat(found.get()).isEqualTo(journalEntry)
    }
}