package org.fm.trackrecord.controller

import org.fm.trackrecord.dao.ActionDao
import org.fm.trackrecord.dao.JournalDao
import org.fm.trackrecord.dao.PositionDao
import org.fm.trackrecord.dao.SecurityDao
import org.fm.trackrecord.entity.Action
import org.fm.trackrecord.entity.JournalEntry
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/action")
class ActionController(private val actionDao: ActionDao, private val journalDao: JournalDao, private val securityDao: SecurityDao) {

    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("actions", actionDao.findAll())
        return "action/list"
    }

    @GetMapping("/{id}")
    fun view(@PathVariable id: Long, model: Model): String {
        model.addAttribute("action", actionDao.findById(id).get())
        return "action/view"
    }

    @GetMapping("/new")
    fun new(model: Model): String {
        model.addAttribute("action", Action(date = java.time.LocalDate.now(), type = org.fm.trackrecord.entity.ActionType.NO_ACTION))
        model.addAttribute("journalEntries", journalDao.findAll())
        model.addAttribute("securities", securityDao.findAll())
        return "action/form"
    }

    @PostMapping
    fun create(@ModelAttribute action: Action): String {
        actionDao.save(action)
        return "redirect:/action"
    }

    @GetMapping("/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        model.addAttribute("action", actionDao.findById(id).get())
        model.addAttribute("journalEntries", journalDao.findAll())
        model.addAttribute("securities", securityDao.findAll())
        return "action/form"
    }

    @PostMapping("/{id}")
    fun update(@PathVariable id: Long, @ModelAttribute action: Action): String {
        actionDao.save(action)
        return "redirect:/action"
    }

    @GetMapping("/{id}/delete")
    fun delete(@PathVariable id: Long): String {
        actionDao.deleteById(id)
        return "redirect:/action"
    }
}
