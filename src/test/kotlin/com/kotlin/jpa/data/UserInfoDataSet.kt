package com.kotlin.jpa.data

import com.kotlin.jpa.entity.UserInfo

class UserInfoDataSet {
    companion object {
        open fun testData(id: Long = 1L, name: String = "test"): UserInfo {
            return UserInfo(id, name)
        }
    }
}
