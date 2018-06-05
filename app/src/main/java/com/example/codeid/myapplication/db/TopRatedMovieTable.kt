package com.example.codeid.myapplication.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException


class TopRatedMovieTable (val db: SQLiteDatabase) {

    var id:Int =0
    var movieID:Int = 0

    fun truncate() {

        var sql = StringBuilder().apply {
            appendln("DELETE FROM $TABLE_NAME;")
        }
        db.execSQL(sql.toString())

        sql = StringBuilder().apply {
            appendln("VACUUM;")
        }
        db.execSQL(sql.toString())

    }


    fun add() {

        val values = ContentValues()
        values.put(TopRatedMovieTable.ID, id)
        values.put(TopRatedMovieTable.MOVIE_ID, movieID)

        try {
            db.insert(TABLE_NAME, null, values)

        }catch(e:Exception) {
        }finally {
            db.close()
        }
    }


    fun list() : List<MovieTable> {

        var listData: MutableList<MovieTable> = ArrayList()

        var sql = StringBuilder().apply {
            appendln("SELECT  b.id, b.title, b.poster_path, b.overview, b.rating_value")
            appendln(" FROM ${TopRatedMovieTable.TABLE_NAME} a ")
            appendln(" INNER JOIN ${MovieTable.TABLE_NAME} b ON a.MovieID = b.id ")
            appendln(" ORDER BY a.id ASC ")
        }

        var c = db.rawQuery(sql.toString(), null)
        db.close()

        while (c.moveToNext()) {

            var movie = MovieTable()

            movie.apply {
                id = c.getInt(0)
                title = c.getString(1)
                posterPath = c.getString(2)
                overview = c.getString(3)
                ratingValue = c.getFloat(4)
            }

            listData.add(movie)
        }

        return listData

    }



    companion object {

        val TABLE_NAME = "top_rated_movie"

        val ID = "id"
        val MOVIE_ID = "movie_id"


        @Throws(SQLiteException::class)
        fun createTable(db: SQLiteDatabase) {

            var sql  = StringBuilder()
            sql.appendln("CREATE TABLE IF NOT EXISTS $TABLE_NAME  ( ")
            sql.appendln(" $ID INTEGER PRIMARY KEY AUTOINCREMENT, ")
            sql.appendln(" $MOVIE_ID INTEGER ) ")

            db.execSQL(sql.toString())
        }

        @Throws(SQLiteException::class)
        fun dropTable(db: SQLiteDatabase) {

            var sql = StringBuilder()
            sql.appendln("DROP TABLE IF EXISTS $TABLE_NAME ")
            db.execSQL(sql.toString())
        }
    }

}