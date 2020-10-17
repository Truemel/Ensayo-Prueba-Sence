package com.example.ensayopruebasence.model.retrofit

import retrofit2.Call
import retrofit2.Callback

class EnsayoRetrofitRequests() {

    private lateinit var onRetroListener:EnsayoRetrofitInterface

    init {
        onRetroListener = EnsayoRetrofitClient.getRetro().create(EnsayoRetrofitInterface::class.java)
    }

    //Request para conseguir la lista de productos
    fun getRetroProducts(callback: Callback<MutableList<EnsayoRetroProduct>>){
        val call:Call<MutableList<EnsayoRetroProduct>> = onRetroListener.getProductList()
        call.enqueue(callback)
    }

    //Request para conseguir detalles de producto
    fun getRetroProductDetail(callback: Callback<EnsayoRetroDetail>, id:Int){
        val call:Call<EnsayoRetroDetail> = onRetroListener.getProductDetail(id)
        call.enqueue(callback)
    }
}