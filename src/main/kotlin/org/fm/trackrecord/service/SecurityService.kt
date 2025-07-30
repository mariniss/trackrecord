package org.fm.trackrecord.service

import org.fm.trackrecord.dao.SecurityDao
import org.fm.trackrecord.entity.Security
import org.springframework.stereotype.Service

@Service
class SecurityService(private val securityDao: SecurityDao) {

    fun findAll(): MutableList<Security> = securityDao.findAll()
}
