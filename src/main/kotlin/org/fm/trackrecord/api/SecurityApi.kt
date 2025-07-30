package org.fm.trackrecord.api

import org.fm.trackrecord.service.SecurityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/security")
class SecurityApi(private val securityService: SecurityService) {

    @GetMapping("/", "")
    fun findAll() = securityService.findAll()
}
