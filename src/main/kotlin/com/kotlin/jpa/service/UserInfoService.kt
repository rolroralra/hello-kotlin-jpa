package com.kotlin.jpa.service

import com.kotlin.jpa.entity.UserInfo
import com.kotlin.jpa.repository.UserInfoRepository
import org.springframework.stereotype.Service

@Service
class UserInfoService(private val userInfoRepository: UserInfoRepository) {
    fun findUserInfo(id: Long) = userInfoRepository.findById(id).orElseThrow{ Exception() }

    fun registerUserInfo(userInfo: UserInfo) = userInfoRepository.save(userInfo)
}
