package org.fm.trackrecord.dao

import org.assertj.core.api.Assertions.assertThat
import org.fm.trackrecord.entity.Portfolio
import org.fm.trackrecord.entity.Position
import org.fm.trackrecord.entity.Security
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.math.BigDecimal
import java.time.LocalDate

@DataJpaTest
class PositionDaoTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val positionDao: PositionDao,
    val securityDao: SecurityDao,
    val portfolioDao: PortfolioDao
) {

    @Test
    fun `When findById then return Position`() {
        val security = Security(ticker = "AAPL", name = "Apple Inc.", currency = "USD")
        entityManager.persist(security)
        val portfolio = Portfolio(name = "test", currency = "USD")
        entityManager.persist(portfolio)
        val position = Position(
            security = security,
            quantity = BigDecimal(10),
            costBasis = BigDecimal(1500),
            openDate = LocalDate.now(),
            portfolio = portfolio
        )
        entityManager.persist(position)
        entityManager.flush()
        val found = positionDao.findById(position.id!!)
        assertThat(found.get()).isEqualTo(position)
    }
}