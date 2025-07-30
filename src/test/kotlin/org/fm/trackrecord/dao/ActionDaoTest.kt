package org.fm.trackrecord.dao

import org.assertj.core.api.Assertions.assertThat
import org.fm.trackrecord.entity.Action
import org.fm.trackrecord.entity.ActionType
import org.fm.trackrecord.entity.JournalEntry
import org.fm.trackrecord.entity.Portfolio
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.LocalDate

@DataJpaTest
class ActionDaoTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val actionDao: ActionDao,
    val journalDao: JournalDao,
    val portfolioDao: PortfolioDao
) {

    @Test
    fun `When findById then return Action`() {
        val portfolio = Portfolio(name = "test", currency = "USD")
        entityManager.persist(portfolio)
        val journalEntry = JournalEntry(
            entryDate = LocalDate.now(),
            title = "test title",
            content = "test content",
            portfolio = portfolio
        )
        entityManager.persist(journalEntry)
        val action = Action(
            date = LocalDate.now(),
            type = ActionType.BUY,
            journalEntry = journalEntry
        )
        entityManager.persist(action)
        entityManager.flush()
        val found = actionDao.findById(action.id!!)
        assertThat(found.get()).isEqualTo(action)
    }
}