package com.malykhnik.bulletinboard_kotlin.service.business_logic

import com.malykhnik.bulletinboard_kotlin.dto.topic_dto.TopicDtoForUpdate
import com.malykhnik.bulletinboard_kotlin.entity.Topic

interface TopicService {
    fun createTopic(topic: Topic): Topic
    fun getAllTopics(): List<Topic>
    fun getTopicById(id: Long): Topic
    fun updateTopic(topicId: Long, topicDtoForUpdate: TopicDtoForUpdate): Topic
    fun deleteTopic(topicId: Long)
}