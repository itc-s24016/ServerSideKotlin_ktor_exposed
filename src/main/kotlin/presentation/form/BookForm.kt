package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.presentation.form

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.BookWithRental
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.RentalWithUser
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class BookInfo(
    val id: Long,
    val title: String,
    val author: String,
    val isRental: Boolean,
) {
    constructor(model: BookWithRental) : this(
        id = model.book.id,
        title = model.book.title,
        author = model.book.author,
        isRental = model.isRental
    )
}

@Serializable
data class GetBookListResponse(
    val books: List<BookInfo>
)

@Serializable
data class RentalLog(
    val id: Long,
    val username: String,
    val rentalDatetime: LocalDateTime,
    val returnDeadline: LocalDateTime,
) {
    constructor(model: RentalWithUser) : this(
        model.id, model.user.name, model.rentalDatetime, model.returnDeadline
    )
}

@Serializable
data class GetBookDetailResponse(
    val id: Long,
    val title: String,
    val author: String,
    val releaseDate: LocalDate,
    val rentalInfo: RentalLog?,
) {
    constructor(model: BookWithRental) : this(
        model.book.id,
        model.book.title,
        model.book.author,
        model.book.releaseDate,
        model.rental?.run(::RentalLog)
    )
}