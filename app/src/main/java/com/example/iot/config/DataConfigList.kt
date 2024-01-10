package com.example.iot.config

import com.example.iot.model.ModelGempaList
import com.example.iot.model.Modelgempa
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class dataconfiglist {

    // set interceptor
    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return  okHttpClient
    }
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://data.bmkg.go.id/DataMKG/TEWS/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService() = getRetrofit().create(EarthQuakeList::class.java)
}

interface EarthQuakeList {
    @GET("gempaterkini.JSON")
    fun getDataGempa(): Call<ModelGempaList>
}