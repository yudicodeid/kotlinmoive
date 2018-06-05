package com.example.codeid.myapplication.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "kotlinmovie.db"
    }

    override fun onCreate(db: SQLiteDatabase) {

        //MovieTable.dropTable(db)
        //PopularMovieTable.dropTable(db)
        //TopRatedMovieTable.dropTable(db)

        MovieTable.createTable(db)
        PopularMovieTable.createTable(db)
        TopRatedMovieTable.createTable(db)

    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {

        MovieTable.dropTable(db)
        PopularMovieTable.dropTable(db)
        TopRatedMovieTable.dropTable(db)

    }

}