package org.fm.trackrecord.service

import org.fm.trackrecord.dao.UserDao
import org.fm.trackrecord.entity.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock
    private lateinit var userDao: UserDao

    @InjectMocks
    private lateinit var userService: UserService

    @Test
    fun `When findAll then return all users`() {
        val user1 = User(name = "first")
        val user2 = User(name = "second")
        `when`(userDao.findAll()).thenReturn(mutableListOf(user1, user2))

        val users = userService.findAll()

        assertEquals(2, users.size)
        assertEquals(user1, users[0])
        assertEquals(user2, users[1])
    }
}