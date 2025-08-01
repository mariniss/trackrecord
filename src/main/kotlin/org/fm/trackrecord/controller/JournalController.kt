package org.fm.trackrecord.controller

import org.fm.trackrecord.dao.JournalDao
import org.fm.trackrecord.dao.PortfolioDao
import org.fm.trackrecord.entity.JournalEntry
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/journal")
class JournalController(private val journalDao: JournalDao, private val portfolioDao: PortfolioDao) {

    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("journalEntries", journalDao.findAll())
        return "journal/list"
    }

    @GetMapping("/{id}")
    fun view(@PathVariable id: Long, model: Model): String {
        model.addAttribute("journalEntry", journalDao.findById(id).get())
        return "journal/view"
    }

    @GetMapping("/new")
    fun new(model: Model): String {
        model.addAttribute("journalEntry", JournalEntry(entryDate = java.time.LocalDate.now(), title = "", content = ""))
        model.addAttribute("portfolios", portfolioDao.findAll())
        return "journal/form"
    }

    @PostMapping
    fun create(@ModelAttribute journalEntry: JournalEntry): String {
        journalDao.save(journalEntry)
        return "redirect:/journal"
    }

    @GetMapping("/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        model.addAttribute("journalEntry", journalDao.findById(id).get())
        model.addAttribute("portfolios", portfolioDao.findAll())
        return "journal/form"
    }

    @PostMapping("/{id}")
    fun update(@PathVariable id: Long, @ModelAttribute journalEntry: JournalEntry): String {
        journalDao.save(journalEntry)
        return "redirect:/journal"
    }

    @GetMapping("/{id}/delete")
    fun delete(@PathVariable id: Long): String {
        journalDao.deleteById(id)
        return "redirect:/journal"
    }
}
