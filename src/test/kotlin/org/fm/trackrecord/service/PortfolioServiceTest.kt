package org.fm.trackrecord.service

import org.fm.trackrecord.dao.PortfolioDao
import org.fm.trackrecord.entity.Portfolio
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class PortfolioServiceTest {

    @InjectMocks
    private lateinit var portfolioService: PortfolioService

    @Mock
    private lateinit var portfolioDao: PortfolioDao

    @Test
    fun `findAll should return all portfolios`() {
        val portfolio1 = Portfolio(id = 1, name = "first", currency = "USD")
        val portfolio2 = Portfolio(id = 2, name = "second", currency = "USD")
        `when`(portfolioDao.findAll()).thenReturn(mutableListOf(portfolio1, portfolio2))
        val portfolios = portfolioService.findAll()
        assert(portfolios.size == 2)
        assert(portfolios.contains(portfolio1))
        assert(portfolios.contains(portfolio2))
    }
}