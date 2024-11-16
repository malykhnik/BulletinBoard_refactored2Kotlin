package com.malykhnik.bulletinboard_kotlin.rest

import com.malykhnik.bulletinboard_kotlin.dto.message_dto.MessageDto
import com.malykhnik.bulletinboard_kotlin.dto.message_dto.MessageDtoForUpdate
import com.malykhnik.bulletinboard_kotlin.entity.Message
import com.malykhnik.bulletinboard_kotlin.entity.Topic
import com.malykhnik.bulletinboard_kotlin.service.business_logic.MessageService
import com.malykhnik.bulletinboard_kotlin.service.business_logic.TopicService
import com.malykhnik.bulletinboard_kotlin.util.WorkWithAuth
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/messages")
class MessageController(
    private val messageService: MessageService,
    private val topicService: TopicService
) {
    @GetMapping("/{topicId}")
    fun getMessagesInTopicByTopicId(@PathVariable(name = "topicId") topicId: Long): ResponseEntity<List<MessageDto>> {
        return ResponseEntity.ok(messageService.getMessagesByTopicId(topicId).toDtoList());
    }

    @PostMapping("/{topicId}")
    fun createMessageInTopicById(@PathVariable(name = "topicId") topicId: Long,
                                 @RequestBody messageDto: MessageDto
    ): ResponseEntity<MessageDto> {
        val topicEntity = topicService.getTopicById(topicId)
        val messageEntity = messageDto.toEntity(topicEntity)
        return ResponseEntity.ok(messageService.createMessage(messageEntity).toDto())
    }

    @PatchMapping("/{topicId}")
    fun updateMessageByUserEmailAndTopicId(@PathVariable(name = "topicId") topicId: Long,
                                           @RequestBody messageDtoForUpdate: MessageDtoForUpdate
    ): ResponseEntity<MessageDto> {
        val topicEntity = topicService.getTopicById(topicId)
        return ResponseEntity.ok(messageService.updateMessage(topicEntity, messageDtoForUpdate).toDto())
    }
}

fun MessageDto.toEntity(topic: Topic): Message {
    return Message(
        id = this.id,
        author = WorkWithAuth.getUsernameByAuthUser()!!,
        message = this.message,
        date = this.date!!,
        topic = topic
    )
}

fun Message.toDto(): MessageDto {
    return MessageDto(
        id = this.id,
        author = this.author,
        message = this.message,
        date = this.date
    )
}

fun List<Message>.toDtoList(): List<MessageDto> = this.map { it.toDto() }