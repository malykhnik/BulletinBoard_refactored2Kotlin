package com.malykhnik.bulletinboard_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
class BulletinBoardKotlinApplication

fun main(args: Array<String>) {
    runApplication<BulletinBoardKotlinApplication>(*args)
}
