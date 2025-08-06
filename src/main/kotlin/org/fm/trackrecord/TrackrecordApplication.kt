package org.fm.trackrecord

import jakarta.annotation.PostConstruct
import org.fm.trackrecord.entity.Portfolio
import org.fm.trackrecord.service.PortfolioService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class TrackrecordApplication

fun main(args: Array<String>) {
    runApplication<TrackrecordApplication>(*args)
}

@Component
class DataLoader(private val portfolioService: PortfolioService) {

    @PostConstruct
    fun loadData() {
        portfolioService.save(Portfolio(name = "Tech Growth", currency = "USD"))
        portfolioService.save(Portfolio(name = "Real Estate", currency = "EUR"))
        portfolioService.save(Portfolio(name = "Crypto", currency = "USD"))
        portfolioService.save(Portfolio(name = "Retirement", currency = "GBP"))
    }
}
