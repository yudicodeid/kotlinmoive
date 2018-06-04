package com.example.codeid.myapplication.api

import android.os.AsyncTask
import android.util.Log
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.math.log


class ConnectionAsyncTask(
        request: Request,
        response: Response) : AsyncTask<String, String, String>() {

    val CONNECTON_TIMEOUT_MILLISECONDS = 60000

    var response: Response = response

    val request: Request? =  request



    override fun onPreExecute() { }

    override fun doInBackground(vararg requests: String): String {

        var urlConnection: HttpURLConnection? = null

        try {

            val url = URL(request?.url)

            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.connectTimeout = CONNECTON_TIMEOUT_MILLISECONDS
            urlConnection.readTimeout = CONNECTON_TIMEOUT_MILLISECONDS

            var inString = streamToString(urlConnection.inputStream)
            publishProgress(inString)

        } catch (ex: Exception) {

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect()
            }
        }

        return " "

    }

    override fun onProgressUpdate(vararg values: String?) {

        try {

            var res = values[0]

            Log.d("RESPONSE", res)

            response?.result = res

            response?.requestType = request!!.requestType

            response?.notifyObservers()

        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }


    override fun onPostExecute(result: String?) { }


    fun streamToString(inputStream: InputStream): String {

        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        var line: String
        var result = ""

        try {
            do {
                line = bufferReader.readLine()
                if (line != null) {
                    result += line
                }
            } while (line != null)
            inputStream.close()
        } catch (ex: Exception) {

        }

        return result

    }

}