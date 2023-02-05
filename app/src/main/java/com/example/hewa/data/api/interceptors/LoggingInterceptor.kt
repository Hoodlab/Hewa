package com.example.hewa.data.api.interceptors

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
/**
*Intercepts any network error and logs it into console
*/
class LoggingInterceptor @Inject constructor(): HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Log.i("LoggingInterceptor", "log: $message")
    }
}