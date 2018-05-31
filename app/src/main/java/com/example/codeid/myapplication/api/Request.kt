package com.example.codeid.myapplication.api

enum class RequestType {
    NONE,
    POPULAR_LIST,
    TOP_RATED
}


class Request (val url:String, var requestType: RequestType)