package com.example.model

import org.jetbrains.exposed.sql.Table
import java.util.SimpleTimeZone

object UserRow: Table(name= "users"){
    val id = integer(name = "user_id").autoIncrement()
    val name = varchar(name = "user_name", length = 250)
    val email = varchar(name="email", length = 250)
    val bio = text(name = "user_bio").default(
        defaultValue = "Hey, what's up?"
    )
    val password = varchar(name = "user_password", length = 100)
    val avatar = text(name= "use_avatar").nullable()

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}

data class User(
    val id: Int,
    val name: String,
    val bio: String,
    val avatar: String?,
    val password: String

)