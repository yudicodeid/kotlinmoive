package com.example.codeid.myapplication.api

import org.json.JSONObject


class ResponseParser  {

    companion object {

        fun parse(value:String?) : List<MovieApiModel> {


            var list:MutableList<MovieApiModel> = ArrayList<MovieApiModel>()


            if (value!=null) {

                var response = JSONObject(value)

                var results = response.getJSONArray("results")

                val len = results.length()

                for( i in 0..(len-1) ) {

                    var result = results.getJSONObject(i)


                    var dVoteAvg = result.getDouble("vote_average")
                    var fVoteAvg = dVoteAvg.toFloat()


                    var ent = MovieApiModel( result.getInt("id"),
                            result.getString("title"),
                            result.getString("overview"),
                            fVoteAvg,
                            result.getString("poster_path") )


                    list.add(ent)


                }

            }

            return list

        }

    }

}