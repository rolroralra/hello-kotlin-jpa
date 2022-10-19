package com.kotlin.jpa.repository

import com.kotlin.jpa.data.UserInfoDataSet
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class UserInfoRepositoryTests {
    @Autowired
    private lateinit var userInfoRepository: UserInfoRepository

    @Test
    fun `정상 저장 테스트`() {
        // Given
        val givenUserInfo = UserInfoDataSet.testData()

        // When
        val savedUserInfo = userInfoRepository.save(givenUserInfo)
        println(savedUserInfo)

        // Then
        assertThat(savedUserInfo).isNotNull
    }
}
