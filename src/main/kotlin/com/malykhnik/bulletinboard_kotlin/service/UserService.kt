package com.malykhnik.bulletinboard_kotlin.service

import com.malykhnik.bulletinboard_kotlin.entity.User

interface UserService {
    fun createUser(user: User): User
    fun findById(id: Long): User
    fun getAllUsers(): List<User>
    fun deleteByID(id: Long)
}