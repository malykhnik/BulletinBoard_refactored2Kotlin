package com.malykhnik.bulletinboard_kotlin.service.business_logic

import com.malykhnik.bulletinboard_kotlin.entity.Message

interface MessageService {
    fun getMessagesByTopicId(topicId: Long): List<Message>;
}