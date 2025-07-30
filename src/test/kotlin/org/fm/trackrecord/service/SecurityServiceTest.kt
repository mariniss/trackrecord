package org.fm.trackrecord.service

import org.fm.trackrecord.dao.SecurityDao
import org.fm.trackrecord.entity.Security
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class SecurityServiceTest {

    @InjectMocks
    private lateinit var securityService: SecurityService

    @Mock
    private lateinit var securityDao: SecurityDao

    @Test
    fun `findAll should return all securities`() {
        val security1 = Security(id = 1, ticker = "AAPL", name = "Apple Inc.", currency = "USD")
        val security2 = Security(id = 2, ticker = "GOOGL", name = "Alphabet Inc.", currency = "USD")
        `when`(securityDao.findAll()).thenReturn(mutableListOf(security1, security2))
        val securities = securityService.findAll()
        assert(securities.size == 2)
        assert(securities.contains(security1))
        assert(securities.contains(security2))
    }
}