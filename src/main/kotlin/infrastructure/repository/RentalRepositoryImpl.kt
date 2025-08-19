package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.repository

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.Book
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.RentalInfo
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.User
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository.RentalRepository
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.entity.BookEntity
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.entity.RentalLogEntity
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.entity.UserEntity
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

class RentalRepositoryImpl(
    private val rentalLogCompanion: RentalLogEntity.Companion,
    private val bookCompanion: BookEntity.Companion,
    private val userCompanion: UserEntity.Companion
) : RentalRepository {
    override fun find(id: Long): RentalInfo? = transaction{
        rentalLogCompanion.findById(id)?.let(::toModel)
    }

    override fun startRental(rental: RentalInfo): RentalInfo = transaction {
        rentalLogCompanion.new {
            book = bookCompanion.findById(rental.book.id)
                ?: throw IllegalStateException("Book not found??")
            user = userCompanion.findById(rental.user.id)
                ?: throw IllegalStateException("User not found??")
            rentalDatetime = rental.rentalDatetime
            returnDeadline = rental.returnDeadline
        }.let(::toModel)
    }

    override fun endRental(rental: RentalInfo): RentalInfo = transaction {
        rentalLogCompanion.findByIdAndUpdate(rental.id) { entity ->
            entity.returnDatetime = rental.rentalDatetime
        }?.let(::toModel) ?: throw IllegalArgumentException("Rental log not found")
    }

    private fun toModel(entity: RentalLogEntity): RentalInfo = entity.run {
        RentalInfo(
            id = id.value,
            book = Book(
                id = book.id.value,
                title = book.title,
                author = book.author,
                releaseDate = book.releaseDate,
            ),
            user = User(
                id = user.id.value,
                name = user.name
            ),
            rentalDatetime = rentalDatetime,
            returnDeadline = returnDeadline,
            returnDatetime = returnDatetime
        )
    }
}