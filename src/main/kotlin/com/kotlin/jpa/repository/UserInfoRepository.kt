package com.kotlin.jpa.repository

import com.kotlin.jpa.entity.UserInfo
import org.springframework.data.jpa.repository.JpaRepository

interface UserInfoRepository: JpaRepository<UserInfo, Long> {
}
