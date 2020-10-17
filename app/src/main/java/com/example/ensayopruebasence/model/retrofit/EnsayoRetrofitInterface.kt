package com.example.ensayopruebasence.model.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EnsayoRetrofitInterface {
    @GET("products")
    fun getProductList():Call<MutableList<EnsayoRetroProduct>>

    @GET("details/{id}")
    fun getProductDetail(@Path("id") id:Int):Call<EnsayoRetroDetail>
}