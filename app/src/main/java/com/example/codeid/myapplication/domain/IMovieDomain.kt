package com.example.codeid.myapplication.domain

interface IMovieDomain {

    fun loadPopularList(page:Int, cached:Boolean)

    fun loadTopRated(page:Int, cached:Boolean)

    fun setMovieDomainListener(listener: IMovieDomainListener)


}