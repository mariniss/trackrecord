package org.fm.trackrecord.controller

import org.fm.trackrecord.service.PortfolioService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class DashboardController(private val portfolioService: PortfolioService) {

    @GetMapping("/{portfolioId}/dashboard")
    fun dashboard(@PathVariable portfolioId: Long, model: Model): String {
        val portfolio = portfolioService.findById(portfolioId)
            .orElseThrow { IllegalArgumentException("No portfolio with id $portfolioId") }

        model.addAttribute("portfolio", portfolio)
        return "dashboard"
    }
}
