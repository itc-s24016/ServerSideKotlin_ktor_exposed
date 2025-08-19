package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.presentation.form

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class PostRentalStartRequest(
    val bookId: Long,
)

@Serializable
data class PostRentalStartResponse(
    val rentalDatetime: LocalDateTime,
    val returnDeadline: LocalDateTime,
)

@Serializable
data class DeleteRentalEndResponse(
    val rentalDatetime: LocalDateTime,
    val returnDeadline: LocalDateTime,
    val returnDatetime: LocalDateTime,
)