package org.fm.trackrecord.dao

import org.assertj.core.api.Assertions.assertThat
import org.fm.trackrecord.entity.Portfolio
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class PortfolioDaoTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val portfolioDao: PortfolioDao
) {

    @Test
    fun `When findById then return Portfolio`() {
        val portfolio = Portfolio(name = "test", currency = "USD")
        entityManager.persist(portfolio)
        entityManager.flush()
        val found = portfolioDao.findById(portfolio.id!!)
        assertThat(found.get()).isEqualTo(portfolio)
    }
}