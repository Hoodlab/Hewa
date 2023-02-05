package com.example.hewa.data.api

object ApiConstants {
    const val API_BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val END_POINT = "weather"
    const val API_KEY = "5efafcadd36adfe0cd258faa95ba69f7"
    fun iconURl(code:String) = "https://openweathermap.org/img/wn/$code@2x.png"
}

object ApiParameters{
    const val APPID = "APPID"
    const val SEARCHPARAM = "q"
}