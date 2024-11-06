package com.example.weathertest.networking

import okhttp3.Headers
import okhttp3.Response
import okhttp3.ResponseBody

public sealed class NetworkError {
    //connection issues
    public data class NoInternet(val throwable: Throwable) : NetworkError()

    public data class Unknown(val throwable: Throwable) : NetworkError()

    public data class HttpFailure(
        val raw: Response,
        val errorBody: ResponseBody?
    ): NetworkError(){
            val code: Int = raw.code
        val message : String = raw.message
        val headers: Headers = raw.headers
    }

}