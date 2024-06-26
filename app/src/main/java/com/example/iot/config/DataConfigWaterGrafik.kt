package com.example.iot.config


import com.example.iot.model.Kapasitas
import com.example.iot.model.ModelGrafik1Water
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class DataConfigWaterGrafik {
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
            .baseUrl("https://rk54tnpc-3000.asse.devtunnels.ms/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService() = getRetrofit().create(WaterReservoirGrafik::class.java)
}

interface WaterReservoirGrafik {
    @GET("dataperhari")
    fun getDataWaterG(): Call<ModelGrafik1Water>
}