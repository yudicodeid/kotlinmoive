package com.example.codeid.myapplication.domain

interface IMovieDomain {

    fun loadPopularList(page:Int)

    fun loadTopRated(page:Int)

    fun loadDetails(id: Int)

    fun setMovieDomainListener(listener: IMovieDomainListener)


}