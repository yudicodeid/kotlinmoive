package com.example.codeid.myapplication.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException

class MovieTable: IMovieTable {

    constructor()

    constructor(db: SQLiteDatabase) {
        this.db = db
    }

    private lateinit var db:SQLiteDatabase
    var id:Int = 0
    var title:String = ""
    var posterPath:String = ""
    var ratingValue:Float = 0.0f
    var overview:String = ""

    override fun findByMovieID(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun find() : Boolean {

        if (id ==0)  throw Exception("Invalid id=0")

        var params = arrayOf(id.toString())

        var sql = StringBuilder().apply {
            appendln("SELECT title, poster_path, rating_value,overview ")
            appendln(" FROM ${MovieTable.TABLE_NAME} WHERE ${MovieTable.ID}=?")
        }

        var c = db.rawQuery(sql.toString(), params)

        if (c.moveToFirst()) {

            title = c.getString(c.getColumnIndex(MovieTable.TITLE))
            posterPath = c.getString(c.getColumnIndex(MovieTable.POSTER_PATH))
            ratingValue = c.getFloat(c.getColumnIndex(MovieTable.RATING_VALUE))
            overview = c.getString(c.getColumnIndex(MovieTable.OVERVIEW))

            return true

        }

        return false

    }

    override fun add() {

        val values = ContentValues()
        values.put(ID, id)
        values.put(TITLE, title)
        values.put(POSTER_PATH, posterPath)
        values.put(RATING_VALUE, ratingValue)
        values.put(OVERVIEW, overview)

        db.insert(TABLE_NAME, null, values)

    }

    override fun list(): List<MovieTable> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun truncate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        val TABLE_NAME = "master_movie"

        val ID = "id"
        val TITLE = "title"
        val POSTER_PATH = "poster_path"
        val RATING_VALUE = "rating_value"
        val OVERVIEW = "overview"


        @Throws(SQLiteException::class)
        fun createTable(db: SQLiteDatabase) {

            var sql  = StringBuilder()
            sql.appendln("CREATE TABLE IF NOT EXISTS $TABLE_NAME ( ")
            sql.appendln(" $ID INTEGER PRIMARY KEY, ")
            sql.appendln(" $TITLE TEXT, ")
            sql.appendln(" $POSTER_PATH TEXT, ")
            sql.appendln(" $RATING_VALUE REAL, ")
            sql.appendln(" $OVERVIEW TEXT ) ")

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