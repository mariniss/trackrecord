package org.fm.trackrecord.controller

import org.fm.trackrecord.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController(private val userService: UserService) {

    @GetMapping("/")
    fun blog(model: Model): String {
        model.addAttribute("users", userService.findAll())
        return "index"
    }
}