package com.kotlin.jpa.entity

import javax.persistence.*

@Entity
@Table(name = "USER_INFO")
class UserInfo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long?,
    var name: String
) {
}
