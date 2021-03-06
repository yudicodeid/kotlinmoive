package com.example.codeid.myapplication.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException


class TopRatedMovieTable (val db: SQLiteDatabase): IMovieTable {

    var id:Int =0
    var movieID:Int = 0

    @Throws(Exception::class)
    override fun truncate() {

        var sql = StringBuilder().apply {
            appendln("DELETE FROM $TABLE_NAME;")
        }
        db.execSQL(sql.toString())

        sql = StringBuilder().apply {
            appendln("VACUUM;")
        }
        db.execSQL(sql.toString())

    }



    @Throws(Exception::class)
    override fun findByMovieID(): Boolean {

        if(movieID ==0) throw Exception("Invalid MovieID=0")

        var sql = StringBuilder().apply {
            appendln("SELECT $ID, $MOVIE_ID FROM ${TopRatedMovieTable.TABLE_NAME} WHERE movie_id=?")
        }

        var params = arrayOf(movieID.toString())
        var c = db.rawQuery(sql.toString(), params)

        if( c.moveToFirst()) {

            id = c.getInt(0)
            movieID = c.getInt(1)

            return true
        }

        return false

    }

    override fun add() {

        val values = ContentValues()
        values.put(TopRatedMovieTable.MOVIE_ID, movieID)

        try {
            db.insert(TABLE_NAME, null, values)

        }catch(e:Exception) {
        }finally {
            db.close()
        }
    }

    override fun find(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun list() : List<MovieTable> {

        var listData: MutableList<MovieTable> = ArrayList()

        var sql = StringBuilder().apply {
            appendln("SELECT  b.id, b.title, b.poster_path, b.overview, b.rating_value")
            appendln(" FROM ${TopRatedMovieTable.TABLE_NAME} a ")
            appendln(" INNER JOIN ${MovieTable.TABLE_NAME} b ON a.movie_id = b.id ")
            appendln(" ORDER BY a.id ASC ")
        }

        var c = db.rawQuery(sql.toString(), null)
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