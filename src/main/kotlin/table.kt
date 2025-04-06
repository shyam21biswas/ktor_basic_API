package com.example

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object StudentTable : Table() {

    val userId : Column<Long> = long("userId").autoIncrement()
    val name : Column<String> = varchar("name", 512)
    val age : Column<Long> = long("age")

    override val primaryKey: PrimaryKey = PrimaryKey(userId)

}