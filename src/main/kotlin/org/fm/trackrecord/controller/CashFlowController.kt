package org.fm.trackrecord.controller

import org.fm.trackrecord.dao.CashFlowDao
import org.fm.trackrecord.dao.PortfolioDao
import org.fm.trackrecord.entity.CashFlow
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/cashflow")
class CashFlowController(private val cashFlowDao: CashFlowDao, private val portfolioDao: PortfolioDao) {

    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("cashFlows", cashFlowDao.findAll())
        return "cashflow/list"
    }

    @GetMapping("/{id}")
    fun view(@PathVariable id: Long, model: Model): String {
        model.addAttribute("cashFlow", cashFlowDao.findById(id).get())
        return "cashflow/view"
    }

    @GetMapping("/new")
    fun new(model: Model): String {
        model.addAttribute("cashFlow", CashFlow(portfolio = portfolioDao.findAll().first(), date = java.time.LocalDate.now(), amount = java.math.BigDecimal.ZERO, type = org.fm.trackrecord.entity.CashFlowType.INFLOW))
        model.addAttribute("portfolios", portfolioDao.findAll())
        return "cashflow/form"
    }

    @PostMapping
    fun create(@ModelAttribute cashFlow: CashFlow): String {
        cashFlowDao.save(cashFlow)
        return "redirect:/cashflow"
    }

    @GetMapping("/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        model.addAttribute("cashFlow", cashFlowDao.findById(id).get())
        model.addAttribute("portfolios", portfolioDao.findAll())
        return "cashflow/form"
    }

    @PostMapping("/{id}")
    fun update(@PathVariable id: Long, @ModelAttribute cashFlow: CashFlow): String {
        cashFlowDao.save(cashFlow)
        return "redirect:/cashflow"
    }

    @GetMapping("/{id}/delete")
    fun delete(@PathVariable id: Long): String {
        cashFlowDao.deleteById(id)
        return "redirect:/cashflow"
    }
}
