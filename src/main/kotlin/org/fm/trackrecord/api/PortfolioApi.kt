package org.fm.trackrecord.api

import org.fm.trackrecord.entity.Portfolio
import org.fm.trackrecord.service.PortfolioService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/portfolio")
class PortfolioApi(private val portfolioService: PortfolioService) {

    @GetMapping("/")
    fun findAll(): MutableList<Portfolio> = portfolioService.findAll()
}
