package com.malykhnik.bulletinboard_kotlin.repository

import com.malykhnik.bulletinboard_kotlin.entity.RefreshToken
import com.malykhnik.bulletinboard_kotlin.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository: JpaRepository<RefreshToken, Long> {
    fun findByUser(user: User): RefreshToken?
    fun findByToken(token: String): RefreshToken?
}