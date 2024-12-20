package com.malykhnik.bulletinboard_kotlin.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val email: String,
    val password: String,
    @Enumerated(EnumType.ORDINAL)
    val role: Role
) {

}