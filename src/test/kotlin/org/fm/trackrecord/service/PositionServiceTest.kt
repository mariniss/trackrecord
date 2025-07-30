package org.fm.trackrecord.service

import org.fm.trackrecord.dao.PositionDao
import org.fm.trackrecord.entity.Portfolio
import org.fm.trackrecord.entity.Position
import org.fm.trackrecord.entity.Security
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class PositionServiceTest {

    @InjectMocks
    private lateinit var positionService: PositionService

    @Mock
    private lateinit var positionDao: PositionDao

    @Test
    fun `findAll should return all positions`() {
        val portfolio = Portfolio(id = 1, name = "first", currency = "USD")
        val security = Security(id = 1, ticker = "AAPL", name = "Apple Inc.", currency = "USD")
        val position1 = Position(id = 1, security = security, quantity = BigDecimal(10), costBasis = BigDecimal(1500), openDate = LocalDate.now(), portfolio = portfolio)
        val position2 = Position(id = 2, security = security, quantity = BigDecimal(20), costBasis = BigDecimal(3000), openDate = LocalDate.now(), portfolio = portfolio)
        `when`(positionDao.findAll()).thenReturn(mutableListOf(position1, position2))
        val positions = positionService.findAll()
        assert(positions.size == 2)
        assert(positions.contains(position1))
        assert(positions.contains(position2))
    }
}