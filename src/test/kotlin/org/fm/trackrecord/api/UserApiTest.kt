package org.fm.trackrecord.api

import org.fm.trackrecord.entity.User
import org.fm.trackrecord.service.UserService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(UserApi::class)
class UserApiTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    private lateinit var userService: UserService

    @Test
    fun `When findAll then return all users`() {
        val user1 = User(id = 1, name = "first")
        val user2 = User(id = 2, name = "second")
        `when`(userService.findAll()).thenReturn(mutableListOf(user1, user2))

        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.[0].id").value(user1.id))
            .andExpect(jsonPath("$.[0].name").value(user1.name))
            .andExpect(jsonPath("$.[1].id").value(user2.id))
            .andExpect(jsonPath("$.[1].name").value(user2.name))
    }
}