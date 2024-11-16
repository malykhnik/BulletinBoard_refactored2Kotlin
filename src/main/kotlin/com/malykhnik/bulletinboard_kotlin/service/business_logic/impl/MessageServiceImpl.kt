package com.malykhnik.bulletinboard_kotlin.service.business_logic.impl

import com.malykhnik.bulletinboard_kotlin.dto.message_dto.MessageDtoForUpdate
import com.malykhnik.bulletinboard_kotlin.entity.Message
import com.malykhnik.bulletinboard_kotlin.entity.Topic
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.message_exceptions.MessageAlreadyExistsException
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.message_exceptions.MessageForUpdateNotFound
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.message_exceptions.MessageNotFoundException
import com.malykhnik.bulletinboard_kotlin.repository.MessageRepository
import com.malykhnik.bulletinboard_kotlin.service.business_logic.MessageService
import com.malykhnik.bulletinboard_kotlin.util.WorkWithAuth
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

    override fun updateMessage(topic: Topic, messageDtoForUpdate: MessageDtoForUpdate): Message {
        val author = WorkWithAuth.getUsernameByAuthUser()
        topic.messages.forEach {
            if (it.author == author) {
                val updatedMessage = changeMessage(it, messageDtoForUpdate)
                return messageRepo.save(updatedMessage)
            }
        }
        throw MessageForUpdateNotFound("Message in topic with ID ${topic.id} and author name equal $author not found")
    }

    fun changeMessage(existing: Message, messageDtoForUpdate: MessageDtoForUpdate): Message {
        return Message(
            id = existing.id,
            author = existing.author,
            message = messageDtoForUpdate.message ?: existing.message,
            date = existing.date,
            topic = existing.topic
        )
    }

}