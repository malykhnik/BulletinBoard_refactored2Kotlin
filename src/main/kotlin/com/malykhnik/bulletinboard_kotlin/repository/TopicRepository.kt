package com.malykhnik.bulletinboard_kotlin.repository

import com.malykhnik.bulletinboard_kotlin.entity.Topic
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository: JpaRepository<Topic, Long> {
}