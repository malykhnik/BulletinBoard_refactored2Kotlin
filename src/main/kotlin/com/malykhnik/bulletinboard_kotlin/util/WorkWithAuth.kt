package com.malykhnik.bulletinboard_kotlin.util

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

class WorkWithAuth {
    companion object{
        fun getUsernameByAuthUser(): String? {
            val auth: Authentication? = SecurityContextHolder.getContext().authentication
            return auth?.name
        }
    }
}