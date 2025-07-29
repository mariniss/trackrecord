package org.fm.trackrecord.api

import org.fm.trackrecord.entity.User
import org.fm.trackrecord.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserApi(private val userService: UserService) {

    @GetMapping
    fun findAll(): MutableList<User> = userService.findAll()
}