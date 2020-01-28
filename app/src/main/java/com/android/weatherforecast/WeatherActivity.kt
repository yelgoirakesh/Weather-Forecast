package com.android.weatherforecast

import WeatherList
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.weatherforecast.model.Json4Kotlin_Base

import com.jessicathornsby.myapplication.WeatherApi
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlincodes.com.retrofitwithkotlin.adapters.DataAdpter
import kotlinx.android.synthetic.main.activity_weather.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WeatherActivity : AppCompatActivity() {

    private var myAdapter: DataAdpter? = null
    private var myCompositeDisposable: CompositeDisposable? = null

    var dataList = ArrayList<WeatherList>()

    val BASE_URL = "https://samples.openweathermap.org/data/2.5/"
    //val appId = "b6907d289e10d714a6e88b30761fae22" as String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        myCompositeDisposable = CompositeDisposable()
        initRecyclerView()
        fetchWeatherData()

    }

//Initialise the RecyclerView//

    private fun initRecyclerView() {

        //Use a layout manager to position your items to look like a standard ListView//

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        rv_data.layoutManager = layoutManager

        myAdapter = DataAdpter(dataList)
        rv_data.adapter = myAdapter

    }

//Implement loadData//

    private fun fetchWeatherData() {

        val requestInterface = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(initOkHttp())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WeatherApi::class.java)

        myCompositeDisposable?.add(
            requestInterface.getWeatherData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Json4Kotlin_Base>() {
                    override fun onSuccess(data: Json4Kotlin_Base) {

                        data?.list?.let {

                            myAdapter?.updateList(it)
                        }
                    }

                    override fun onError(e: Throwable) {

                        Log.d("error ", "" + e.printStackTrace())
                    }
                })
        )
    }


    private fun initOkHttp(): OkHttpClient {
        val REQUEST_TIMEOUT = 60

        val httpClient = OkHttpClient().newBuilder()

            .addInterceptor(ChuckInterceptor(this))
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(interceptor)

        var okHttpClient: OkHttpClient = httpClient.build()

        return okHttpClient;
    }


    override fun onDestroy() {
        super.onDestroy()

        //Clear all your disposables

        myCompositeDisposable?.clear()

    }

}