package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model

import kotlinx.datetime.LocalDate

data class Book (
    val id: Long,
    val title: String,
    val author: String,
    val releaseDate: LocalDate
)