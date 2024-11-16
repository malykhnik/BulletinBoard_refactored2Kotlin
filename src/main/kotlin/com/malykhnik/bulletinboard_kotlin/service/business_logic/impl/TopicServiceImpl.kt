package com.malykhnik.bulletinboard_kotlin.service.business_logic.impl

import com.malykhnik.bulletinboard_kotlin.entity.Topic
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.topic_exceptions.TopicAlreadyExistsException
import com.malykhnik.bulletinboard_kotlin.exception.custom_exception.topic_exceptions.TopicsNotFoundException
import com.malykhnik.bulletinboard_kotlin.repository.TopicRepository
import com.malykhnik.bulletinboard_kotlin.service.business_logic.TopicService
import org.springframework.stereotype.Service

@Service
class TopicServiceImpl(
    private val topicRepo: TopicRepository
): TopicService {
    override fun createTopic(topic: Topic): Topic{
        if (topicRepo.existsById(topic.id)) {
            throw TopicAlreadyExistsException("Topic with ID ${topic.id} already exists")
        }
        return topicRepo.save(topic)
    }
    override fun getAllTopics(): List<Topic> {
        val topics = topicRepo.findAll()
        if (topics.isEmpty()) {
            throw TopicsNotFoundException("No topics found")
        }
        return topics
    }

    override fun getTopicById(id: Long): Topic {
        return topicRepo.findById(id)
            .orElseThrow { TopicsNotFoundException("Topic with id $id not found") }
    }
}