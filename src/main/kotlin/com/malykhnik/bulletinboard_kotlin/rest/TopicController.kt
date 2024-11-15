package com.malykhnik.bulletinboard_kotlin.rest

import com.malykhnik.bulletinboard_kotlin.dto.TopicDto
import com.malykhnik.bulletinboard_kotlin.entity.Topic
import com.malykhnik.bulletinboard_kotlin.service.business_logic.TopicService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topics")
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

fun Topic.toDto() = TopicDto(
    id = id,
    title = title,
    messages = messages
)

fun TopicDto.toEntity() = Topic(
    id = id,
    title = title,
    messages = messages
)

fun List<Topic>.toDtoList(): List<TopicDto> = this.map { it.toDto() }
