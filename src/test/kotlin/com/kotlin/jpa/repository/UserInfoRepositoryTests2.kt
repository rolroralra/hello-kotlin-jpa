package com.kotlin.jpa.repository

import com.kotlin.jpa.data.UserInfoDataSet
import com.kotlin.jpa.entity.UserInfo
import com.kotlin.jpa.repository.common.JpaRepositoryTests
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository

class UserInfoRepositoryTests2: JpaRepositoryTests<UserInfo, Long>() {
    @Autowired
    private lateinit var userInfoRepository: UserInfoRepository

    override fun repository(): JpaRepository<UserInfo, Long> = userInfoRepository

    override fun createTestInstance(): UserInfo = UserInfoDataSet.testData()

    override fun idFromEntity(entity: UserInfo): Long = entity.id!!
}
