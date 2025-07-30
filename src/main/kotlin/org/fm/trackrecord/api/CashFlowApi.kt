package org.fm.trackrecord.api

import org.fm.trackrecord.service.CashFlowService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/cash-flow")
class CashFlowApi(private val cashFlowService: CashFlowService) {

    @GetMapping("/", "")
    fun findAll() = cashFlowService.findAll()
}