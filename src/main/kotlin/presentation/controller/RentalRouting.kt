package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.presentation.controller

import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.server.plugins.di.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import io.ktor.server.routing.application
import io.ktor.server.sessions.*
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.application.service.RentalService
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.UserPrincipal
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.presentation.form.DeleteRentalEndResponse
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.presentation.form.PostRentalStartRequest
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.presentation.form.PostRentalStartResponse

/**
 * 貸出に関するルーティングを定義します。
 */
fun Route.rentalRouting() {
    val service: RentalService by application.dependencies

    // 貸出を開始するエンドポイント
    // リクエストボディから書籍IDを受け取り、貸出を開始します。
    // セッションに保存されているユーザー情報を使用して、
    // 貸出の開始日時と返却期限を返します。
    post<Rental.Start> { params ->
        // リクエストボディ(JSONデータ)を取り出す
        val body = call.receive<PostRentalStartRequest>()
        // セッションに保存されているログイン情報を取り出す。なければ 400 エラーを返して終わる。
        val user = call.sessions.get<UserPrincipal>() ?: return@post call.respond(HttpStatusCode.BadRequest)
        val rental = service.startRental(body.bookId, user.id)
        call.respond(
            HttpStatusCode.OK, PostRentalStartResponse(
                rental.rentalDatetime, rental.returnDeadline
            )
        )
    }

    // 貸出を終了するエンドポイント
    // パスパラメータから貸出IDを受け取り、貸出を終了します。
    // セッションに保存されているユーザー情報を使用して、
    // 貸出の開始日時・返却期限・返却日時を返します。
    delete<Rental.End> { params ->
        val user = call.sessions.get<UserPrincipal>() ?: return@delete call.respond(HttpStatusCode.BadRequest)
        val rental = service.endRental(params.id, user.id)
        call.respond(HttpStatusCode.OK, rental.run {
            DeleteRentalEndResponse(
                rentalDatetime,
                returnDeadline,
                returnDatetime ?: throw IllegalStateException("What, return_datetime is null")
            )
        })
    }
}

/**
 * 貸出のリクエストパスリソースを定義します。
 */
@Resource("/rental")
class Rental {
    @Resource("/start")
    class Start(val rental: Rental = Rental())

    @Resource("/end/{id}")
    class End(val rental: Rental = Rental(), val id: Long)
}