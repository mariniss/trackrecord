package org.fm.trackrecord.dao

import org.assertj.core.api.Assertions.assertThat
import org.fm.trackrecord.entity.Security
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class SecurityDaoTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val securityDao: SecurityDao
) {

    @Test
    fun `When findById then return Security`() {
        val security = Security(ticker = "AAPL", name = "Apple Inc.", currency = "USD")
        entityManager.persist(security)
        entityManager.flush()
        val found = securityDao.findById(security.id!!)
        assertThat(found.get()).isEqualTo(security)
    }
}