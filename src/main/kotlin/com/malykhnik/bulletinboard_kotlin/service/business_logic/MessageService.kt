package com.malykhnik.bulletinboard_kotlin.service.business_logic

import com.malykhnik.bulletinboard_kotlin.dto.MessageDto
import com.malykhnik.bulletinboard_kotlin.entity.Message

interface MessageService {
    fun getMessagesByTopicId(topicId: Long): List<Message>
    fun createMessage(message: Message): Message
}