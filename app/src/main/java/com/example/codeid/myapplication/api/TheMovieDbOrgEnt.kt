package com.example.codeid.myapplication.api

/*

{
  "page": 1,
  "total_results": 19865,
  "total_pages": 994,
  "results": [
    {
      "vote_count": 1282,
      "id": 383498,
      "video": false,
      "vote_average": 7.9,
      "title": "Deadpool 2",
      "popularity": 497.250634,
      "poster_path": "/to0spRl1CMDvyUbOnbb4fTk3VAd.jpg",
      "original_language": "en",
      "original_title": "Deadpool 2",
      "genre_ids": [
        28,
        35,
        878
      ],
      "backdrop_path": "/3P52oz9HPQWxcwHOwxtyrVV1LKi.jpg",
      "adult": false,
      "overview": "Wisecracking mercenary Deadpool battles the evil and powerful Cable and other bad guys to save a boy's life.",
      "release_date": "2018-05-15"
    },

 */
data class TheMovieDbResult(val page:Int, val total_results:Int, val total_pages:Int, val result:List<TheMovieDbOrgEnt>)

data class TheMovieDbOrgEnt(val id:Int, val title: String?, val vote_average: Float, val poster_path: String?)