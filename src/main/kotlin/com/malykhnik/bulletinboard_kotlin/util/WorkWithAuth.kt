package com.malykhnik.bulletinboard_kotlin.util

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

class WorkWithAuth {
    companion object{
        fun getUsernameByAuthUser(): String? {
            val auth: Authentication? = SecurityContextHolder.getContext().authentication
            return auth?.name
        }
        fun getRolesByAuthUser(): List<String>? {
            val auth: Authentication? = SecurityContextHolder.getContext().authentication
            return auth?.authorities?.map { it.authority }
        }

        fun findAdminRole(): Boolean {
            val roles = getRolesByAuthUser()
            println(roles.toString())
            return roles?.contains("ROLE_ADMIN") == true
        }
    }
}