package org.fm.trackrecord.api

import org.fm.trackrecord.service.ActionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/action")
class ActionApi(private val actionService: ActionService) {

    @GetMapping("/", "")
    fun findAll() = actionService.findAll()
}
