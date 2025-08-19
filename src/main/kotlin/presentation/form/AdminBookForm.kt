package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.presentation.form

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class PostBookRegisterRequest(
    val title: String,
    val author: String,
    val releaseDate: LocalDate
)

@Serializable
data class BookInfoResponse(
    val id: Long,
    val title: String,
    val author: String,
    val releaseDate: LocalDate,
)

@Serializable
data class PutBookUpdateRequest(
    val id: Long,
    val title: String?,
    val author: String?,
    val releaseDate: LocalDate?
)