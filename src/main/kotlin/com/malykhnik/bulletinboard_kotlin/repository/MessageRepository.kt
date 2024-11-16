package com.malykhnik.bulletinboard_kotlin.repository

import com.malykhnik.bulletinboard_kotlin.entity.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository: JpaRepository<Message, Long> {
    fun findByTopicId(topicId: Long): List<Message>?
}