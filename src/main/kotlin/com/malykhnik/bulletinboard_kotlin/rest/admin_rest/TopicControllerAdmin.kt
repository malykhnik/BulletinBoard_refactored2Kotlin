package com.malykhnik.bulletinboard_kotlin.rest.admin_rest

import com.malykhnik.bulletinboard_kotlin.dto.topic_dto.TopicDto
import com.malykhnik.bulletinboard_kotlin.dto.message_dto.MessageDto
import com.malykhnik.bulletinboard_kotlin.dto.topic_dto.TopicDtoForUpdate
import com.malykhnik.bulletinboard_kotlin.entity.Message
import com.malykhnik.bulletinboard_kotlin.entity.Topic
import com.malykhnik.bulletinboard_kotlin.service.business_logic.TopicService
import com.malykhnik.bulletinboard_kotlin.util.WorkWithAuth
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/topics")
class TopicControllerAdmin(
    private val topicsService: TopicService
) {
    @PatchMapping("/{topicId}")
    fun updateTopicById(@PathVariable(name = "topicId") topicId: Long,
                                          @RequestBody topicDtoForUpdate: TopicDtoForUpdate
    ): ResponseEntity<TopicDto> {
        return ResponseEntity.ok(topicsService.updateTopic(topicId, topicDtoForUpdate).toDto())
    }

    @DeleteMapping("/{topicId}")
    fun deleteTopicById(@PathVariable(name = "topicId") topicId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(topicsService.deleteTopic(topicId))
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
