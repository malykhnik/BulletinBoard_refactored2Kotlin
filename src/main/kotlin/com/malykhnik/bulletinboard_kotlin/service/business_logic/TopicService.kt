package com.malykhnik.bulletinboard_kotlin.service.business_logic

import com.malykhnik.bulletinboard_kotlin.entity.Topic

interface TopicService {
    fun createTopic(topic: Topic): Topic
    fun getAllTopics(): List<Topic>
}