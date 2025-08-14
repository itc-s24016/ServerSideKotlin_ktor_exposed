package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.application.service

import de.mkammerer.argon2.Argon2Factory
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.UserPrincipal
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.repository.UserRepository

class AuthenticationsService (
    private val repository: UserRepository
){
    // Argon2のインスタンスを生成
    private val argon2 = Argon2Factory.create(
        // ハッシュアルゴリズムを指定
        Argon2Factory.Argon2Types.ARGON2id
    )

    fun authenticate(email: String, password: String): UserPrincipal? {
        //条件: ユーザが存在しない場合はnullを返す
        val user = repository.find(email) ?: return null
        //条件: パスワードが正しい場合
        //処理: verifyメソッドでパスワードの検証を行う
        //引数：ハッシュ化されたパスワード, 入力値をCharArrayに変換したもの
        //成功: UserPrincipalを返す
        return if (argon2.verify(user.password, password.toCharArray())) {
            user
        } else {
            null
        }
    }
}