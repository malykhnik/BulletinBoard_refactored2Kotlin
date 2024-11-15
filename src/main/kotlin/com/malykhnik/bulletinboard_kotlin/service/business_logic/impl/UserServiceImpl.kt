package com.malykhnik.bulletinboard_kotlin.service.business_logic.impl

import com.malykhnik.bulletinboard_kotlin.entity.User
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.user_exceptions.UserAlreadyExistsException
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.user_exceptions.UsersNotFoundException
import com.malykhnik.bulletinboard_kotlin.repository.UserRepository
import com.malykhnik.bulletinboard_kotlin.service.business_logic.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepo: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {
    override fun createUser(user: User): User {
        if (userRepo.existsById(user.id)) {
            throw UserAlreadyExistsException("User with ID ${user.id} already exists")
        }
        return userRepo
            .save(
                User(
                    user.id,
                    user.email,
                    passwordEncoder.encode(user.password),
                    user.role
                )
            )
    }

    override fun findById(id: Long): User {
        return userRepo.findById(id).orElseThrow {
            UserAlreadyExistsException("User with ID $id doesn't exists")
        }
    }

    override fun getAllUsers(): List<User> {
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