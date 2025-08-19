package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.AuthenticationStrategy
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.Resources
import io.ktor.server.routing.routing
import jp.ac.it_college.std.s24000.kotlin.ktor.book.manager.presentation.controller.adminBookRouting
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.presentation.controller.bookRouting
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.presentation.controller.rentalRouting

fun Application.configureRouting() {
    install(Resources)
    routing {
        // strategy に Required を指定すると、ネストした authenticate で外側から順に認証情報をチェックします。
        authenticate("user-session" ,strategy = AuthenticationStrategy.Required) {
            bookRouting()
            rentalRouting()
            authenticate("admin-session", strategy = AuthenticationStrategy.Required) {
                adminBookRouting()
            }
        }
    }
}