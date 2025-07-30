package org.fm.trackrecord.api

import org.fm.trackrecord.entity.Security
import org.fm.trackrecord.service.SecurityService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(SecurityApi::class)
class SecurityApiTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    private lateinit var securityService: SecurityService

    @Test
    fun `findAll should return all securities`() {
        val security1 = Security(id = 1, ticker = "AAPL", name = "Apple Inc.", currency = "USD")
        val security2 = Security(id = 2, ticker = "GOOGL", name = "Alphabet Inc.", currency = "USD")
        `when`(securityService.findAll()).thenReturn(mutableListOf(security1, security2))

        mockMvc.perform(get("/api/security/"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.size()").value(2))
            .andExpect(jsonPath("$[0].ticker").value("AAPL"))
            .andExpect(jsonPath("$[1].ticker").value("GOOGL"))
    }
}