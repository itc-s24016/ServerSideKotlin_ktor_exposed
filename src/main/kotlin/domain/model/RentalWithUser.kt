package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model

import kotlinx.datetime.LocalDateTime

// レンタル情報とユーザー情報を結合したデータクラス
data class RentalWithUser(
    val id: Long,
    val user: User,
    val rentalDatetime: LocalDateTime,
    val returnDeadline: LocalDateTime
){
    //Spring Boot 版との互換性のために追加
    //※Spring Boot 版では User エンティティの id を直接参照していたから
    val userId: Long
        get() = user.id
}
