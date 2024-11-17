package com.malykhnik.bulletinboard_kotlin.util

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

class WorkWithAuth {
    companion object {
        fun getUsernameByAuthUser(): String {
            val auth: Authentication? = SecurityContextHolder.getContext().authentication
            if (auth != null && auth.isAuthenticated) {
                val userDetails = auth.principal as UserDetails
                return userDetails.username
            }
            throw IllegalStateException("User is not authenticated")
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