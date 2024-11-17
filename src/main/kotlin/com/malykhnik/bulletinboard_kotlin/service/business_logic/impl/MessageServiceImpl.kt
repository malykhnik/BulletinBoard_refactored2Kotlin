package com.malykhnik.bulletinboard_kotlin.service.business_logic.impl

import com.malykhnik.bulletinboard_kotlin.dto.message_dto.MessageDtoForUpdate
import com.malykhnik.bulletinboard_kotlin.entity.Message
import com.malykhnik.bulletinboard_kotlin.entity.Topic
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.message_exceptions.MessageAlreadyExistsException
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.message_exceptions.MessageForUpdateNotFoundException
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.message_exceptions.MessageNotFoundException
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.topic_exceptions.CountMessagesInTopicException
import com.malykhnik.bulletinboard_kotlin.repository.MessageRepository
import com.malykhnik.bulletinboard_kotlin.service.business_logic.MessageService
import com.malykhnik.bulletinboard_kotlin.util.WorkWithAuth
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(
    private val messageRepo: MessageRepository
) : MessageService {
    override fun getMessagesByTopicId(topicId: Long): List<Message> {
        return messageRepo.findByTopicId(topicId) ?: throw MessageNotFoundException("Messages by $topicId not found")
    }

    override fun createMessage(message: Message): Message {
        if (messageRepo.existsById(message.id)) {
            throw MessageAlreadyExistsException("Message with ID ${message.id} already exists")
        }
        return messageRepo.save(message)
    }

    override fun updateMessage(messageId: Long, messageDtoForUpdate: MessageDtoForUpdate): Message {
        val messageEntity = messageRepo.findById(messageId)
            .orElseThrow { MessageNotFoundException("Messages by $messageId not found") }
        if (WorkWithAuth.findAdminRole()) {
            val updatedMessage = changeMessage(messageEntity, messageDtoForUpdate)
            return messageRepo.save(updatedMessage)
        }
        val author = WorkWithAuth.getUsernameByAuthUser()
        if (messageEntity.author == author) {
            val updatedMessage = changeMessage(messageEntity, messageDtoForUpdate)
            return messageRepo.save(updatedMessage)
        }
        throw MessageForUpdateNotFoundException("Message with ID $messageId and author name equals $author not found")
    }

    override fun deleteMessage(messageId: Long) {
        if (!messageRepo.existsById(messageId)) {
            throw MessageAlreadyExistsException("Message with ID $messageId not found")
        }
        val author = WorkWithAuth.getUsernameByAuthUser()
        val messageEntity = messageRepo.findById(messageId).get()
        val topic = messageEntity.topic
        if (WorkWithAuth.findAdminRole() && checkCountMessagesInTopic(topic)) {
            topic.messages.remove(messageEntity)
            return messageRepo.delete(messageEntity)
        }
        if (checkAuthorName(messageEntity, author) && checkCountMessagesInTopic(topic)) {
            topic.messages.remove(messageEntity)
            return messageRepo.delete(messageEntity)
        }
        throw CountMessagesInTopicException("Message in topic could not be equal 0")
    }

    private fun changeMessage(existing: Message, messageDtoForUpdate: MessageDtoForUpdate): Message {
        return Message(
            id = existing.id,
            author = existing.author,
            message = messageDtoForUpdate.message ?: existing.message,
            date = existing.date,
            topic = existing.topic
        )
    }

    private fun checkAuthorName(message: Message,
                                author: String?): Boolean = message.author == author


    private fun checkCountMessagesInTopic(topic: Topic): Boolean = topic.messages.size > 1


}