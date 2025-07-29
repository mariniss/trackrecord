package org.fm.trackrecord.controller

import org.fm.trackrecord.entity.User
import org.fm.trackrecord.service.UserService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(HtmlController::class)
class HtmlControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    private lateinit var userService: UserService

    @Test
    fun `When GET then return index`() {
        val user1 = User(id = 1, name = "first")
        val user2 = User(id = 2, name = "second")
        `when`(userService.findAll()).thenReturn(mutableListOf(user1, user2))

        mockMvc.perform(get("/"))
            .andExpect(status().isOk)
            .andExpect(view().name("index"))
            .andExpect(model().attributeExists("users"))
            .andExpect(model().attribute("users", userService.findAll()))
    }
}