package com.malykhnik.bulletinboard_kotlin.rest

import com.malykhnik.bulletinboard_kotlin.dto.message_dto.MessageDto
import com.malykhnik.bulletinboard_kotlin.dto.TopicDto
import com.malykhnik.bulletinboard_kotlin.entity.Message
import com.malykhnik.bulletinboard_kotlin.entity.Topic
import com.malykhnik.bulletinboard_kotlin.service.business_logic.TopicService
import com.malykhnik.bulletinboard_kotlin.util.WorkWithAuth
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/topics")
class TopicController(
    private val topicService: TopicService
) {
    @PostMapping("/create")
    fun createTopic(@RequestBody topicDto: TopicDto): ResponseEntity<TopicDto> {
        val topicEntity = topicDto.toEntity()
        return ResponseEntity.ok(topicService.createTopic(topicEntity).toDto())
    }

    @GetMapping
    fun getAllTopics(): ResponseEntity<List<TopicDto>> {
        return ResponseEntity.ok(topicService.getAllTopics().toDtoList())
    }

}

fun TopicDto.toEntity(): Topic {
    val topic = Topic(
        id = this.id,
        title = this.title
    )
    topic.messages.addAll(
        this.messages.map { messageDto ->
            Message(
                id = messageDto.id,
                author = WorkWithAuth.getUsernameByAuthUser()!!,
                message = messageDto.message,
                date = messageDto.date!!,
                topic = topic
            )
        }
    )
    return topic
}

fun Topic.toDto(): TopicDto {
    return TopicDto(
        id = this.id,
        title = this.title,
        messages = this.messages.map { message ->
            MessageDto(
                id = message.id,
                author = message.author,
                message = message.message,
                date = message.date
            )
        }.toMutableList()
    )
}

fun List<Topic>.toDtoList(): List<TopicDto> = this.map { it.toDto() }
