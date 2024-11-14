package com.malykhnik.bulletinboard_kotlin.repository

import com.malykhnik.bulletinboard_kotlin.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
}