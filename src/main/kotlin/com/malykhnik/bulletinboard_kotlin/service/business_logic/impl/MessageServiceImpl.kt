package com.malykhnik.bulletinboard_kotlin.service.business_logic.impl

import com.malykhnik.bulletinboard_kotlin.dto.MessageDto
import com.malykhnik.bulletinboard_kotlin.entity.Message
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.message_exceptions.MessageAlreadyExistsException
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.message_exceptions.MessageNotFoundException
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.topic_exceptions.TopicAlreadyExistsException
import com.malykhnik.bulletinboard_kotlin.repository.MessageRepository
import com.malykhnik.bulletinboard_kotlin.service.business_logic.MessageService
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(
    private val messageRepo: MessageRepository
): MessageService {
    override fun getMessagesByTopicId(topicId: Long): List<Message> {
        return messageRepo.findByTopicId(topicId) ?: throw MessageNotFoundException("Messages by $topicId not found")
    }

    override fun createMessage(message: Message): Message {
        if (messageRepo.existsById(message.id)) {
            throw MessageAlreadyExistsException("Message with ID ${message.id} already exists")
        }
        return messageRepo.save(message)
    }
}