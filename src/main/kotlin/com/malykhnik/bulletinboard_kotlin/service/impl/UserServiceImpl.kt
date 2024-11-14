package com.malykhnik.bulletinboard_kotlin.service.impl

import com.malykhnik.bulletinboard_kotlin.entity.User
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.topic_exceptions.TopicsNotFoundException
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.user_exceptions.UserAlreadyExistsException
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.user_exceptions.UsersNotFoundException
import com.malykhnik.bulletinboard_kotlin.repository.UserRepository
import com.malykhnik.bulletinboard_kotlin.service.UserService
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepo: UserRepository
) : UserService {
    override fun createUser(user: User): User {
        if (userRepo.existsById(user.id)) {
            throw UserAlreadyExistsException("User with ID ${user.id} already exists")
        }
        return userRepo.save(user)
    }

    override fun findById(id: Long): User {
        return userRepo.findById(id).orElseThrow {
            UserAlreadyExistsException("User with ID $id doesn't exists")
        }
    }

    override fun findAll(): List<User> {
        val users = userRepo.findAll()
        if (users.isEmpty()) {
            throw UsersNotFoundException("No users found")
        }
        return users
    }

    override fun deleteByID(id: Long) {
        if (userRepo.existsById(id)) {
            throw UserAlreadyExistsException("User with ID $id doesn't exists")
        }
        return userRepo.deleteById(id)
    }
}