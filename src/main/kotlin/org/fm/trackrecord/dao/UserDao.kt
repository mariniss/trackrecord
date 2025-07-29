package org.fm.trackrecord.dao

import org.fm.trackrecord.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserDao : JpaRepository<User, Long>