package org.fm.trackrecord.service

import org.fm.trackrecord.dao.UserDao
import org.fm.trackrecord.entity.User
import org.springframework.stereotype.Service

@Service
class UserService(private val userDao: UserDao) {

    fun findAll(): MutableList<User> = userDao.findAll()
}