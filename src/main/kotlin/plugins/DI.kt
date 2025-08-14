package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.plugins

import io.ktor.server.application.Application
import io.ktor.server.plugins.di.dependencies
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.application.service.AuthenticationsService
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.application.service.BookService
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository.BookRepository
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository.UserRepository
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.repository.BookRepositoryImpl
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.repository.UserRepositoryImpl

fun Application.configureDI() {
    // DIの設定を行う
    dependencies {
        /* provides:インスタンスを生成する関数
           provide<この型が請求されたら>(この型のインスタンスを作る) */
        provide<BookRepository>(BookRepositoryImpl::class)
        provide<UserRepository>(UserRepositoryImpl::class)
        provide(BookService::class)
        provide(AuthenticationsService::class)
    }
}