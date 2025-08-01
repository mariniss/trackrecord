package org.fm.trackrecord.controller

import org.fm.trackrecord.dao.SecurityDao
import org.fm.trackrecord.entity.Security
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/security")
class SecurityController(private val securityDao: SecurityDao) {

    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("securities", securityDao.findAll())
        return "security/list"
    }

    @GetMapping("/{id}")
    fun view(@PathVariable id: Long, model: Model): String {
        model.addAttribute("security", securityDao.findById(id).get())
        return "security/view"
    }

    @GetMapping("/new")
    fun new(model: Model): String {
        model.addAttribute("security", Security(name = "", ticker = "", currency = "USD"))
        return "security/form"
    }

    @PostMapping
    fun create(@ModelAttribute security: Security): String {
        securityDao.save(security)
        return "redirect:/security"
    }

    @GetMapping("/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        model.addAttribute("security", securityDao.findById(id).get())
        return "security/form"
    }

    @PostMapping("/{id}")
    fun update(@PathVariable id: Long, @ModelAttribute security: Security): String {
        securityDao.save(security)
        return "redirect:/security"
    }

    @GetMapping("/{id}/delete")
    fun delete(@PathVariable id: Long): String {
        securityDao.deleteById(id)
        return "redirect:/security"
    }
}
