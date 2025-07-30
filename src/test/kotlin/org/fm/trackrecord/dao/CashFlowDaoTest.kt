package org.fm.trackrecord.dao

import org.assertj.core.api.Assertions.assertThat
import org.fm.trackrecord.entity.CashFlow
import org.fm.trackrecord.entity.CashFlowType
import org.fm.trackrecord.entity.Portfolio
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.math.BigDecimal
import java.time.LocalDate

@DataJpaTest
class CashFlowDaoTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val cashFlowDao: CashFlowDao,
    val portfolioDao: PortfolioDao
) {

    @Test
    fun `When findById then return CashFlow`() {
        val portfolio = Portfolio(name = "test", currency = "USD")
        entityManager.persist(portfolio)
        val cashFlow = CashFlow(
            date = LocalDate.now(),
            amount = BigDecimal(1000),
            type = CashFlowType.INFLOW,
            portfolio = portfolio
        )
        entityManager.persist(cashFlow)
        entityManager.flush()
        val found = cashFlowDao.findById(cashFlow.id!!)
        assertThat(found.get()).isEqualTo(cashFlow)
    }
}