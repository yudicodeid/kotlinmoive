package com.example.codeid.myapplication.db

import android.database.sqlite.SQLiteException

interface IMovieTable {

    fun list(): List<MovieTable>
    fun add()
    fun find() : Boolean

    @Throws(SQLiteException::class)
    fun findByMovieID() : Boolean

    @Throws(SQLiteException::class)
    fun truncate()

}
