package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model

import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.types.RoleType
import kotlinx.serialization.Serializable

// ログイン時に必要な情報を保持するデータクラス
@Serializable
data class UserPrincipal(
    val id: Long,
    val email: String,
    val password: String,
    val name: String,
    val roleType: RoleType
)
