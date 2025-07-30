package org.fm.trackrecord.api

import org.fm.trackrecord.service.PositionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/position")
class PositionApi(private val positionService: PositionService) {

    @GetMapping("/", "")
    fun findAll() = positionService.findAll()
}
