package jp.ac.it_college.std.s24000.kotlin.ktor.book.manager.presentation.controller

import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.server.plugins.di.*
import io.ktor.server.request.*
import io.ktor.server.resources.delete
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import io.ktor.server.routing.application
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.application.service.AdminBookService
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.domain.model.Book
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.presentation.form.PostBookRegisterRequest
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.presentation.form.BookInfoResponse
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.presentation.form.PutBookUpdateRequest

/**
 * 管理者権限が必要な書籍に関するルーティングを定義します。
 */
fun Route.adminBookRouting() {
    val service: AdminBookService by application.dependencies

    // 書籍の登録エンドポイント
    // リクエストボディから書籍情報を受け取り、新しい書籍を登録します。
    // 登録された書籍の情報をレスポンスとして返します。
    // 成功した場合は 201 Created ステータスコードを返します
    post<AdminBook.Register> { params ->
        val body = call.receive<PostBookRegisterRequest>()
        val newBook = service.register(body.run {
            Book(
                id = 0, // IDは自動生成されるため、0を指定
                title = title,
                author = author,
                releaseDate = releaseDate,
            )
        })
        call.respond(HttpStatusCode.Created, newBook.run {
            BookInfoResponse(id, title, author, releaseDate)
        })
    }

    // 書籍の更新エンドポイント
    // リクエストボディから書籍IDと更新情報を受け取り、
    // 指定された書籍の情報を更新します。
    // 更新された書籍の情報をレスポンスとして返します。
    put<AdminBook.Update> { params ->
        val body = call.receive<PutBookUpdateRequest>()
        val updatedBook = body.run {
            service.update(id, title, author, releaseDate)
        }
        call.respond(HttpStatusCode.OK, updatedBook.run {
            BookInfoResponse(id, title, author, releaseDate)
        })
    }

    // 書籍の削除エンドポイント
    // パスパラメータから書籍IDを受け取り、指定された書籍を削除します。
    // 成功した場合は 204 No Content ステータスコードを返します。
    delete<AdminBook.Delete> { params ->
        service.delete(params.id)
        call.respond(HttpStatusCode.NoContent)
    }
}

/**
 * 管理者権限が必要な書籍のリクエストパスリソースを定義します。
 */
@Resource("/admin/book")
class AdminBook {
    @Resource("/register")
    class Register(val adminBook: AdminBook = AdminBook())

    @Resource("/update")
    class Update(val adminBook: AdminBook = AdminBook())

    @Resource("/delete/{id}")
    class Delete(val adminBook: AdminBook = AdminBook(), val id: Long)
}