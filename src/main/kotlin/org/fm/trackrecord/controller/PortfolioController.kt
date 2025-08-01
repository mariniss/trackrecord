package org.fm.trackrecord.controller

import org.fm.trackrecord.dao.PortfolioDao
import org.fm.trackrecord.entity.Portfolio
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/portfolio")
class PortfolioController(private val portfolioDao: PortfolioDao) {

    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("portfolios", portfolioDao.findAll())
        return "portfolio/list"
    }

    @GetMapping("/{id}")
    fun view(@PathVariable id: Long, model: Model): String {
        model.addAttribute("portfolio", portfolioDao.findById(id).get())
        return "portfolio/view"
    }

    @GetMapping("/new")
    fun new(model: Model): String {
        model.addAttribute("portfolio", Portfolio(name = "", currency = "USD"))
        return "portfolio/form"
    }

    @PostMapping
    fun create(@ModelAttribute portfolio: Portfolio): String {
        portfolioDao.save(portfolio)
        return "redirect:/portfolio"
    }

    @GetMapping("/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        model.addAttribute("portfolio", portfolioDao.findById(id).get())
        return "portfolio/form"
    }

    @PostMapping("/{id}")
    fun update(@PathVariable id: Long, @ModelAttribute portfolio: Portfolio): String {
        portfolioDao.save(portfolio)
        return "redirect:/portfolio"
    }

    @GetMapping("/{id}/delete")
    fun delete(@PathVariable id: Long): String {
        portfolioDao.deleteById(id)
        return "redirect:/portfolio"
    }
}
