package com.malykhnik.bulletinboard_kotlin.service.security_logic

import com.malykhnik.bulletinboard_kotlin.configuration.properties.JwtProperties
import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_in_dto.AuthResponseDto
import com.malykhnik.bulletinboard_kotlin.dto.auth_dto.sign_in_dto.SignInRequestDto
import com.malykhnik.bulletinboard_kotlin.entity.RefreshToken
import com.malykhnik.bulletinboard_kotlin.repository.RefreshTokenRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService(
    private val authManager: AuthenticationManager,
    private val jwtService: JwtService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenService: RefreshTokenService
) {
    fun authentication(signInRequestDto: SignInRequestDto): AuthResponseDto {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                signInRequestDto.email,
                signInRequestDto.password
            )
        )
        val accessToken = createAccessToken(signInRequestDto.email)
        val refreshToken = refreshTokenService.createOrUpdateRefreshToken(signInRequestDto.email)

        return AuthResponseDto(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    fun createAccessToken(email: String) = jwtService.generate(
        email = email,
        expirationDate = getAccessTokenExpiration()
    )

    private fun getAccessTokenExpiration(): Date =
        Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
}