package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.repository

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.Book
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.BookWithRental
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.RentalWithUser
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.User
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository.BookRepository
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.entity.BookWithRentalLogEntity
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

// domain/repository/BookRepository.kt を実装するクラス
class BookRepositoryImpl : BookRepository {
    override fun findAllWithRental(): List<BookWithRental> {
        //return BookWithRentalLogEntity.all() をdomainモデルに変換して返す
        return transaction { BookWithRentalLogEntity.all().map(::toModel) }

    }

    //スコープ関数でより短縮する
    private fun toModel(entity: BookWithRentalLogEntity): BookWithRental = entity.run {
        BookWithRental(
            Book(
                id = id.value,
                title = title,
                author = author,
                releaseDate = releaseDate
            ),
            currentLog?.run {
                RentalWithUser(
                    id = id.value,
                    user = User(user.id.value, user.name),
                    rentalDatetime = rentalDatetime,
                    returnDeadline = returnDeadline
                )
            }
        )
    }
}