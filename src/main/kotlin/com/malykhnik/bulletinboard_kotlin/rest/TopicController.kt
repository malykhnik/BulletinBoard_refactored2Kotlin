package com.malykhnik.bulletinboard_kotlin.rest

import com.malykhnik.bulletinboard_kotlin.dto.message_dto.MessageDto
import com.malykhnik.bulletinboard_kotlin.dto.topic_dto.TopicDto
import com.malykhnik.bulletinboard_kotlin.entity.Message
import com.malykhnik.bulletinboard_kotlin.entity.Topic
import com.malykhnik.bulletinboard_kotlin.service.business_logic.TopicService
import com.malykhnik.bulletinboard_kotlin.util.*
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
        return ResponseEntity.ok(topicService.createTopic( topicDto.toEntity()).toDto())
    }

    @GetMapping
    fun getAllTopics(): ResponseEntity<List<TopicDto>> {
        return ResponseEntity.ok(topicService.getAllTopics().toTopicDtoList())
    }

}

