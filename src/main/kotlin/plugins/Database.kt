package jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.plugins

import io.ktor.server.application.Application
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.table.Books
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.table.RentalLogs
import jp.ac.it_college.std.s24016.kotlin.ktor.book.manager.infrastructure.database.table.Users
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

// データベースの接続を設定する
// Ktorアプリケーションの設定ファイルからデータベースの接続情報を取得し、Exposedライブラリを使用して接続する
fun Application.configureDatabase() {
    Database.connect(
        url = environment.config.property("database.url").getString(),
        driver = environment.config.property("database.driver").getString(),
        user = environment.config.property("database.user").getString(),
        password = environment.config.property("database.password").getString()
    )
    // Exposedライブラリを使用して、データベース(Books、Users、RentalLogs)のスキーマを作成する
    transaction {
        SchemaUtils.create(
            Books, Users, RentalLogs,
        )
    }
}