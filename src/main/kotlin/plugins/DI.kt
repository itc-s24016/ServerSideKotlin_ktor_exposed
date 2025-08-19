package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.plugins

import io.ktor.server.application.Application
import io.ktor.server.plugins.di.dependencies
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.application.service.AdminBookService
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.application.service.AuthenticationsService
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.application.service.BookService
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.application.service.RentalService
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository.BookRepository
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository.RentalRepository
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository.UserRepository
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.entity.BookEntity
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.entity.BookWithRentalLogEntity
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.entity.RentalLogEntity
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.entity.UserEntity
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.repository.BookRepositoryImpl
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.repository.RentalRepositoryImpl
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.repository.UserRepositoryImpl

fun Application.configureDI() {
    // DIの設定を行う
    dependencies {
        // 各エンティティクラスのコンパニオンオブジェクトのインジェクション
        provide<BookEntity.Companion> { BookEntity }
        provide<RentalLogEntity.Companion> { RentalLogEntity }
        provide<UserEntity.Companion> { UserEntity }
        provide<BookWithRentalLogEntity.Companion> { BookWithRentalLogEntity }

        // 各リポジトリのインジェクション
        provide<BookRepository>(BookRepositoryImpl::class)
        provide<UserRepository>(UserRepositoryImpl::class)
        provide<RentalRepository>(RentalRepositoryImpl::class)

        // 各サービスのインジェクション
        provide(BookService::class)
        provide(AuthenticationsService::class)
        provide(RentalService::class)
        provide(AdminBookService::class)
    }
}