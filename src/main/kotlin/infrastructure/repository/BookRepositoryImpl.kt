package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.repository

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.Book
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.BookWithRental
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.RentalWithUser
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.User
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository.BookRepository
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.entity.BookEntity
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.entity.BookWithRentalLogEntity
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.table.Books
import kotlinx.datetime.LocalDate
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

// domain/repository/BookRepository.kt を実装するクラス
class BookRepositoryImpl(
    val bookWithRentalLogCompanion: BookWithRentalLogEntity.Companion,
    val bookCompanion: BookEntity.Companion,
) : BookRepository {

    override fun findAllWithRental(): List<BookWithRental> = transaction {
        bookWithRentalLogCompanion.find {
            Books.isDeleted eq false
        }.map(::toModel)
    }

    override fun findWithRental(id: Long): BookWithRental? = transaction {
        bookWithRentalLogCompanion.find {
            Books.isDeleted eq false and (Books.id eq id)
        }.singleOrNull()?.let(::toModel)
    }

    override fun register(book: Book): Book = transaction {
        bookCompanion.new {
            title = book.title
            author = book.author
            releaseDate = book.releaseDate
        }.let(::toModel)
    }

    override fun update(
        id: Long,
        title: String?,
        author: String?,
        releaseDate: LocalDate?
    ): Book = transaction {
        bookCompanion.findByIdAndUpdate(id) { entity ->
            title?.let { entity.title = it }
            author?.let { entity.author = it }
            releaseDate?.let { entity.releaseDate = it }
        } ?: throw IllegalArgumentException("Book with id $id not found")
    }.let(::toModel)


    override fun delete(id: Long) = transaction {
        bookCompanion.findByIdAndUpdate(id) { entity ->
            entity.isDeleted = true
        } ?: throw IllegalArgumentException("Book with id $id not found")
        Unit
    }

    private fun toModel(entity: BookEntity): Book = entity.run {
        Book(
            id = id.value,
            title = title,
            author = author,
            releaseDate = releaseDate
        )
    }

    private fun toModel(entity: BookWithRentalLogEntity): BookWithRental = entity.run {
        BookWithRental(
            book = Book(
                id = id.value,
                title = title,
                author = author,
                releaseDate = releaseDate
            ),
            rental = currentLog?.run {
                RentalWithUser(
                    id = id.value,
                    user = User(user.id.value, user.name),
                    rentalDatetime = rentalDatetime,
                    returnDeadline = returnDeadline,
                )
            }
        )
    }
}