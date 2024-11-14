package com.malykhnik.bulletinboard_kotlin.exception.exception_handler

import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.topic_exceptions.TopicAlreadyExistsException
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.topic_exceptions.TopicsNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class TopicExceptionHandler {

    @ExceptionHandler(TopicAlreadyExistsException::class)
    fun handleTopicAlreadyExists(ex: TopicAlreadyExistsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(TopicsNotFoundException::class)
    fun handleTopicsNotFound(ex: TopicsNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }
}