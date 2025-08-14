package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.Resources
import io.ktor.server.routing.routing
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.presentation.controller.bookRouting

fun Application.configureRouting() {
    install(Resources)
    routing {
        authenticate("user-session"){
            bookRouting()
        }
    }
}