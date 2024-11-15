package com.malykhnik.bulletinboard_kotlin.service.security_logic

import org.springframework.security.core.userdetails.User
import com.malykhnik.bulletinboard_kotlin.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = com.malykhnik.bulletinboard_kotlin.entity.User

@Service
class MyUserDetailsService(
    private val userRepo: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        userRepo.findByEmail(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("Not found!")

    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.email)
            .password(this.password)
            .roles(this.role.name)
            .build()
}