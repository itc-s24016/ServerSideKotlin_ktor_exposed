package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager

import io.ktor.server.application.*
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.plugins.configureDatabase

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureDatabase()
}
