package com.malykhnik.bulletinboard_kotlin.service.business_logic

import com.malykhnik.bulletinboard_kotlin.dto.message_dto.MessageDtoForUpdate
import com.malykhnik.bulletinboard_kotlin.entity.Message
import com.malykhnik.bulletinboard_kotlin.entity.Topic

interface MessageService {
    fun getMessagesByTopicId(topicId: Long): List<Message>
    fun createMessage(message: Message): Message
    fun updateMessage(topic: Topic, messageDtoForUpdate: MessageDtoForUpdate): Message
}