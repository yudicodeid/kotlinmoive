package com.example.codeid.myapplication.api

import java.util.*


class MovieApi : IMovieApi, Observer {

    private val apiKey: String = "dab5969b9d2d0c83940b0a6de8af87a3"

    private val baseUrl: String = "https://api.themoviedb.org/3"

    private var listener: IMovieApiListener? = null


    fun makeRequest(url: String, requestType: RequestType) {

        val fullUrl = baseUrl + url

        var request = Request(fullUrl, RequestType.POPULAR_LIST)

        var response = Response()

        response.addObserver(this)

        val connection = ConnectionAsyncTask(request, response)

        connection.execute("")

    }

    override fun getPopularList(page: Int) {

        var url = "/movie/popular?api_key=" + apiKey

        makeRequest(url, RequestType.POPULAR_LIST)
    }

    override fun getTopRated(page: Int) {
    }

    override fun update(p0: Observable?, p1: Any?) {

        var response: Response = p0 as Response

        var listMovies = ResponseParser.parse(response.result)

        listener?.onListDataPop(listMovies)

    }

    override fun setMovieApiListener(listener: IMovieApiListener) {

        this.listener = listener

    }

}
