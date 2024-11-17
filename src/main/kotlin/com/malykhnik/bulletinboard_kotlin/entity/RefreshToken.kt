package com.malykhnik.bulletinboard_kotlin.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "refresh_tokens")
class RefreshToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val token: String,

    val expiryDate: Instant,

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User
)