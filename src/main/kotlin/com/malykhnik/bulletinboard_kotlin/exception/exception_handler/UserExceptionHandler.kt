package com.malykhnik.bulletinboard_kotlin.exception.exception_handler

import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.message_exceptions.MessageAlreadyExistsException
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.message_exceptions.MessageNotFoundException
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.user_exceptions.UserAlreadyExistsException
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.user_exceptions.UsersNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class UserExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleUserAlreadyExists(ex: UserAlreadyExistsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(UsersNotFoundException::class)
    fun handleUsersNotFound(ex: UsersNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }
}