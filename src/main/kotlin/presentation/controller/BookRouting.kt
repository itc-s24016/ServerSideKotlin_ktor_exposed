package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.presentation.controller

import io.ktor.resources.Resource
import io.ktor.server.plugins.di.dependencies
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.application.service.BookService
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.presentation.form.BookInfo
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.presentation.form.GetBookListResponse

fun Route.bookRouting() {
    val service: BookService by application.dependencies
    get<Books.List> {
        val books = service.getList().map(::BookInfo)
        call.respond(GetBookListResponse(books))
    }
}

@Resource("/books")
class Books {
    @Resource("/list")
    class List(val books: Books = Books())
}