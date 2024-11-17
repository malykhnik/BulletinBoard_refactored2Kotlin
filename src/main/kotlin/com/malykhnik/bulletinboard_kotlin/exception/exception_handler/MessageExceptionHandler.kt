package com.malykhnik.bulletinboard_kotlin.exception.exception_handler

import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.message_exceptions.MessageAlreadyExistsException
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.message_exceptions.MessageForUpdateNotFoundException
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.message_exceptions.MessageNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MessageExceptionHandler {
    @ExceptionHandler(MessageAlreadyExistsException::class)
    fun handleMessageAlreadyExists(ex: MessageAlreadyExistsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(MessageNotFoundException::class)
    fun handleMessageNotFound(ex: MessageNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    @ExceptionHandler(MessageForUpdateNotFoundException::class)
    fun handleMessageForUpdateNotFound(ex: MessageForUpdateNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }
}