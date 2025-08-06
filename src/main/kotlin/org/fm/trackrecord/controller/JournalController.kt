package org.fm.trackrecord.controller

import org.fm.trackrecord.entity.JournalEntry
import org.fm.trackrecord.service.JournalService
import org.fm.trackrecord.service.PortfolioService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import java.time.LocalDate

@Controller
class JournalController(private val portfolioService: PortfolioService, private val journalService: JournalService) {

    @GetMapping("/{portfolioId}/journal")
    fun journal(@PathVariable portfolioId: Long, model: Model): String {
        val journalEntries = journalService.findAllByPortfolioId(portfolioId)
        model.addAttribute("portfolioId", portfolioId)
        model.addAttribute("journalEntries", journalEntries)
        return "journal"
    }

    @GetMapping("/{portfolioId}/journal/add")
    fun showAddJournalEntryForm(@PathVariable portfolioId: Long, model: Model): String {
        model.addAttribute("portfolioId", portfolioId)
        model.addAttribute("journalEntry", JournalEntry(entryDate = LocalDate.now(), title = "", content = ""))
        return "add-journal-entry"
    }

    @PostMapping("/{portfolioId}/journal/add")
    fun addJournalEntry(@PathVariable portfolioId: Long, @ModelAttribute journalEntry: JournalEntry): String {
        journalService.addNew(journalEntry, portfolioId)
        return "redirect:/{portfolioId}/journal"
    }
}
