package com.example.plugins

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*

fun Application.configureSecurity() {
    
    authentication {
            jwt {
                val jwtAudience = System.getenv("jwt.audience")
                verifier(
                    JWT
                        .require(Algorithm.HMAC256(System.getenv("jwt.secret")))
                        .withAudience(jwtAudience)
                        .withIssuer(System.getenv("jwt.domain"))
                        .build()
                )
                validate { credential ->
                    if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
                }
            }
        }
}
