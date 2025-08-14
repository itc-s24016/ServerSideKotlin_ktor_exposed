package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.di.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.application.service.AuthenticationsService
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.UserPrincipal
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.types.RoleType

fun Application.configureAuthentication() {
    val service: AuthenticationsService by dependencies

    install(Authentication) {
        form("user_login") {
            userParamName = "email"
            passwordParamName = "password"

            validate { credentials ->
                credentials.run {
                    service.authenticate(name, password)
                }
            }

            challenge {
                call.respond(
                    HttpStatusCode.Unauthorized,
                    "Invalid email or password."
                )
            }
        }
        session<UserPrincipal>("user-session") {
            validate { session ->
                session
            }

            challenge {
                call.respond(
                    HttpStatusCode.Unauthorized,
                    "Session expired or invalid."
                )
            }
        }

        session<UserPrincipal>("admin-session") {
            validate { session ->
                session.takeIf { it.roleType == RoleType.Admin }
            }

            challenge {
                call.respond(
                    HttpStatusCode.Forbidden,
                    "Admin access requied."
                )
            }
        }
    }
    routing {
        authenticate("user_login") {
            post("/login") {
                val principal = call.principal<UserPrincipal>()
                    ?: return@post call.respond(
                        HttpStatusCode.InternalServerError,
                        "No principal found."
                    )
                call.sessions.set(principal)
                call.respond(
                    HttpStatusCode.OK,
                    "Login successful."
                )
            }
        }
    }
}
