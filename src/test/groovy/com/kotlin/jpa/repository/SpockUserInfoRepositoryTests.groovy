package com.kotlin.jpa.repository

import com.kotlin.jpa.data.SpockUserInfoDataSet
import com.kotlin.jpa.entity.UserInfo
import com.kotlin.jpa.repository.common.JpaRepositoryTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository

class SpockUserInfoRepositoryTests extends JpaRepositoryTest<UserInfo, Long> {
    @Autowired
    private UserInfoRepository userInfoRepository

    @Override
    protected JpaRepository<UserInfo, Long> repository() {
        return userInfoRepository
    }

    @Override
    protected UserInfo createTestInstance() {
        return SpockUserInfoDataSet.testData()
    }

    @Override
    protected Long idFromEntity(UserInfo entity) {
        return entity.id
    }
}
