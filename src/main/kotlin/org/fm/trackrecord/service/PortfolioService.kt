package org.fm.trackrecord.service

import org.fm.trackrecord.dao.PortfolioDao
import org.fm.trackrecord.entity.Portfolio
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PortfolioService(private val portfolioDao: PortfolioDao) {

    fun findAll(): MutableList<Portfolio> = portfolioDao.findAll()

    fun findById(id: Long): Optional<Portfolio> = portfolioDao.findById(id)

    fun save(portfolio: Portfolio): Portfolio = portfolioDao.save(portfolio)
}
