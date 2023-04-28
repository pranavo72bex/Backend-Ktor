package com.example.route

import com.example.model.AuthResponse
import com.example.model.SignUpParams
import com.example.repository.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.authRouting(){
    val repository by inject<UserRepository>()

    route(path = "/signup"){
        post {
            val params = call.receiveNullable<SignUpParams>()

            if(params == null){
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = AuthResponse(
                        errorMessage = "Invalid Credential"
                    )
                )
                return@post
            }

            val result = repository.signUp(params = params)

            call.respond(
                status = result.code,
                message = result.data
            )
        }
    }
}