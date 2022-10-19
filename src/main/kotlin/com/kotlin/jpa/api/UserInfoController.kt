package com.kotlin.jpa.api

import com.kotlin.jpa.entity.UserInfo
import com.kotlin.jpa.service.UserInfoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/users"])
class UserInfoController(private val userInfoService: UserInfoService) {
    @PostMapping
    fun addUserInfo(@RequestBody userInfo: UserInfo): UserInfo {
        val savedUserInfo = userInfoService.registerUserInfo(userInfo)
        return userInfoService.findUserInfo(savedUserInfo.id!!)
    }

    @GetMapping("/{id}")
    fun findUserInfo(@PathVariable id: Long): UserInfo = userInfoService.findUserInfo(id)

}
