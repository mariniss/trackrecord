package org.fm.trackrecord.service

import org.fm.trackrecord.dao.PortfolioDao
import org.fm.trackrecord.entity.Portfolio
import org.springframework.stereotype.Service

@Service
class PortfolioService(private val portfolioDao: PortfolioDao) {

    fun findAll(): MutableList<Portfolio> = portfolioDao.findAll()
}
