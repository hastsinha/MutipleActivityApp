package com.example.mutipleactivityapp.network

import com.example.mutipleactivityapp.modal.Responses
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("page/browse")
    fun getProducts(
        @Query("page_number") pageNumber: Int,
        @Query("tags") tags: String,
        @Query("sortby") sortBy: String,
        @Query("preferred_products") preferredProducts: String
    ): Observable<Responses>
}

object ApiService {
    val instance: ApiInterface
    val httpClient = OkHttpClient.Builder().build()
    const val BASE_URL = "https://page.bluestone.com/"
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        instance = retrofit.create(ApiInterface::class.java)
    }
}