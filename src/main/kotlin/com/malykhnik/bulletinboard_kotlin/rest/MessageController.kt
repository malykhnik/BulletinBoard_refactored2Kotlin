package com.malykhnik.bulletinboard_kotlin.rest

import com.malykhnik.bulletinboard_kotlin.dto.MessageDto
import com.malykhnik.bulletinboard_kotlin.dto.TopicDto
import com.malykhnik.bulletinboard_kotlin.entity.Message
import com.malykhnik.bulletinboard_kotlin.entity.Topic
import com.malykhnik.bulletinboard_kotlin.service.business_logic.MessageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/messages")
class MessageController(
    private val messageService: MessageService
) {
    @GetMapping("/{topicId}")
    fun getMessagesInTopicByTopicId(@PathVariable(name = "topicId") topicId: Long): ResponseEntity<List<MessageDto>> {
        return ResponseEntity.ok(messageService.getMessagesByTopicId(topicId).toDtoList());
    }
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