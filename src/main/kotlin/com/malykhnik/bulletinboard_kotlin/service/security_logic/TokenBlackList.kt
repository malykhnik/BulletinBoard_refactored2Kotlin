package com.malykhnik.bulletinboard_kotlin.service.security_logic

import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class TokenBlackList {
    // Метод добавляет токен в черный список (кэш)
    @CachePut(value = ["blacklist"], key = "#token")
    fun addToBlackList(token: String): String {
        println("CachePut выполнился")
        // Возвращаемое значение будет сохранено в кэше с ключом, равным значению переменной "token".
        return token
    }

    // Если токен есть в кэше, вернется сам токен, иначе вернется null
    @Cacheable(value = ["blacklist"], key = "#token")
    fun isBlackListed(token: String): String? {
        println("Cacheable выполнился")
        return null
    }

}