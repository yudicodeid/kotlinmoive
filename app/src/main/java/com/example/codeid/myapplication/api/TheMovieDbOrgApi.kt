package com.example.codeid.myapplication.api

class TheMovieDbOrgApi : IMovieApi {

    private val apiKey: String = "dab5969b9d2d0c83940b0a6de8af87a3"

    private val api:IRetrofitTheMovieDbOrgApi by lazy {

        RetrofitTheMovieDbOrgApiWrapper.createApi()
    }

    override fun getPopularList(page: Int) {

        api.getPopularList(apiKey, "en-US", page)

    }

    override fun getTopRated(page: Int) {
        val call = api.getPopularList(apiKey, "en-US", page)

    }

}
