package org.fm.trackrecord.api

import org.fm.trackrecord.service.JournalService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/journalEntry")
class JournalApi(private val journalService: JournalService) {

    @GetMapping("/", "")
    fun findAll() = journalService.findAll()
}
