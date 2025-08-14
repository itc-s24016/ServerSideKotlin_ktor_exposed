package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager

import io.ktor.server.application.*
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.plugins.configureAuthentication
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.plugins.configureContentNegotiation
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.plugins.configureDI
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.plugins.configureDatabase
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.plugins.configureRouting
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.plugins.configureSessions

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureDatabase()
    configureDI()
    configureSessions()
    configureAuthentication()
    configureRouting()
    configureContentNegotiation()
}
