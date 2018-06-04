package com.example.codeid.myapplication.api

import java.util.*

class ResponseObserver : Observer {

    override fun update(p0: Observable?, p1: Any?) {

        var response: Response? = p0 as Response

        var list = ResponseParser.parse(response?.result)

    }

}

class Response : Observable() {

    override fun notifyObservers() {
        setChanged()
        super.notifyObservers()
    }

    var result: String? = null
    var requestType: RequestType = RequestType.NONE


}