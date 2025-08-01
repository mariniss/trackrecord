package org.fm.trackrecord.controller

import org.fm.trackrecord.dao.PortfolioDao
import org.fm.trackrecord.dao.PositionDao
import org.fm.trackrecord.dao.SecurityDao
import org.fm.trackrecord.entity.Position
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/position")
class PositionController(private val positionDao: PositionDao, private val portfolioDao: PortfolioDao, private val securityDao: SecurityDao) {

    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("positions", positionDao.findAll())
        return "position/list"
    }

    @GetMapping("/{id}")
    fun view(@PathVariable id: Long, model: Model): String {
        model.addAttribute("position", positionDao.findById(id).get())
        return "position/view"
    }

    @GetMapping("/new")
    fun new(model: Model): String {
        model.addAttribute("position", Position(portfolio = portfolioDao.findAll().first(), security = securityDao.findAll().first(), quantity = java.math.BigDecimal.ZERO, costBasis = java.math.BigDecimal.ZERO, openDate = java.time.LocalDate.now()))
        model.addAttribute("portfolios", portfolioDao.findAll())
        model.addAttribute("securities", securityDao.findAll())
        return "position/form"
    }

    @PostMapping
    fun create(@ModelAttribute position: Position): String {
        positionDao.save(position)
        return "redirect:/position"
    }

    @GetMapping("/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        model.addAttribute("position", positionDao.findById(id).get())
        model.addAttribute("portfolios", portfolioDao.findAll())
        model.addAttribute("securities", securityDao.findAll())
        return "position/form"
    }

    @PostMapping("/{id}")
    fun update(@PathVariable id: Long, @ModelAttribute position: Position): String {
        positionDao.save(position)
        return "redirect:/position"
    }

    @GetMapping("/{id}/delete")
    fun delete(@PathVariable id: Long): String {
        positionDao.deleteById(id)
        return "redirect:/position"
    }
}
