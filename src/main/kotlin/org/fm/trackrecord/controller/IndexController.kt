package org.fm.trackrecord.controller

import org.fm.trackrecord.service.PortfolioService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController(private val portfolioService: PortfolioService) {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("portfolios", portfolioService.findAll())
        return "index"
    }
}