package com.malykhnik.bulletinboard_kotlin.service.security_logic

import com.malykhnik.bulletinboard_kotlin.configuration.properties.JwtProperties
import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_in_dto.JwtResponseDto
import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_in_dto.SignInRequestDto
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: MyUserDetailsService,
    private val jwtService: JwtService,
    private val jwtProperties: JwtProperties,
) {
    fun authentication(signInRequestDto: SignInRequestDto): JwtResponseDto {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                signInRequestDto.email,
                signInRequestDto.password
            )
        )
        val user = userDetailsService.loadUserByUsername(signInRequestDto.email)
        val accessToken = createAccessToken(user)
        return JwtResponseDto(
            accessToken = accessToken,
        )
    }
    private fun createAccessToken(user: UserDetails) = jwtService.generate(
        userDetails = user,
        expirationDate = getAccessTokenExpiration()
    )
    private fun getAccessTokenExpiration(): Date =
        Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
}