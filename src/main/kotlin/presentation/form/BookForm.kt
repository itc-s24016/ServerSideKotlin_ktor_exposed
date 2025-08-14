package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.presentation.form

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.BookWithRental
import kotlinx.serialization.Serializable

@Serializable
data class BookInfo(
    val id: Long,
    val title: String,
    val author: String,
    val isRental: Boolean
){
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