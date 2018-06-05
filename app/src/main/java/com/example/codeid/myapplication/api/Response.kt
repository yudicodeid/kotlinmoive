package com.example.codeid.myapplication.api

import java.util.*


class Response : Observable() {

    override fun notifyObservers() {
        setChanged()
        super.notifyObservers()
    }

    var result: String? = null
    var requestType: RequestType = RequestType.NONE


}