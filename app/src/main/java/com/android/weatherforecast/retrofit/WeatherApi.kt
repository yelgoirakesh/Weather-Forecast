package com.jessicathornsby.myapplication
import com.android.weatherforecast.model.Json4Kotlin_Base
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET


interface WeatherApi {

    @GET("forecast?q=london&appid=b6907d289e10d714a6e88b30761fae22")
    fun getWeatherData(): Single<Json4Kotlin_Base>
}
