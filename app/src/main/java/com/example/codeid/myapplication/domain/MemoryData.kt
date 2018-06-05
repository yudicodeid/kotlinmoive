package com.example.codeid.myapplication.domain

import com.example.codeid.myapplication.api.MovieApiModel
import com.example.codeid.myapplication.db.MovieTable
import com.example.codeid.myapplication.model.MovieModel
import java.util.ArrayList

class MemoryData {

    var data: MutableList<MovieModel> = ArrayList()

    fun clearCache() {
        data.clear()
    }

    fun updateData(apiModelList: List<MovieApiModel>) {

        var copy = data.toMutableList()

        var sorted = sortMovieModelList(copy)

        for(apiModel: MovieApiModel in apiModelList) {

            var found = binarySearch(sorted, apiModel.id)

            if (found == null) {

                var movieModel = MovieModel()

                movieModel.mapFromApiModel( apiModel)

                data.add(movieModel)

            }
        }
    }



    fun updateFromLocal(listMovieTable: List<MovieTable>) {

        var copy = data.toMutableList()
        var sorted = sortMovieModelList(copy)

        for(table: MovieTable in  listMovieTable) {

            var found = binarySearch(sorted, table.id)

            if (found == null) {
                var movieModel = MovieModel()
                movieModel.mapFromTable(table)
                data.add(movieModel)
            }

        }
    }


    protected fun sortMovieModelList(items:List<MovieModel>):List<MovieModel>{
        if (items.count() < 2){
            return items
        }
        val pivot = items[items.count()/2]

        val equal = items.filter { it.id == pivot.id }
        val less = items.filter { it.id < pivot.id }
        val greater = items.filter { it.id > pivot.id }

        return sortMovieModelList(less) + equal + sortMovieModelList(greater)

    }


    protected fun binarySearch(list: List<MovieModel>, key: Int): Int? {

        var rangeStart = 0
        var rangeEnd = list.count()

        while (rangeStart < rangeEnd) {
            val midIndex = rangeStart + (rangeEnd - rangeStart)/2
            if (list[midIndex].id == key) {
                return midIndex
            } else if (list[midIndex].id < key) {
                rangeStart = midIndex + 1
            } else {
                rangeEnd = midIndex
            }
        }

        return null
    }

}