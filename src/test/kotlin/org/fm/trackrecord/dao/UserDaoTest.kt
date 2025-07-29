package org.fm.trackrecord.dao

import org.fm.trackrecord.entity.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class UserDaoTest(@Autowired val entityManager: TestEntityManager,
                  @Autowired val userDao: UserDao) {

    @Test
    fun `When findAll then return all users`() {
        val user1 = User(name = "first")
        entityManager.persist(user1)
        val user2 = User(name = "second")
        entityManager.persist(user2)
        entityManager.flush()

        val users = userDao.findAll()

        assertEquals(2, users.size)
        assertEquals(user1, users[0])
        assertEquals(user2, users[1])
    }
}