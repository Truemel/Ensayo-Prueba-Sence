package com.example.ensayopruebasence.model.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EnsayoRetrofitClient {

    companion object{
        private const val PATH:String = "http://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"
        private var retro:Retrofit? = null

        fun getRetro():Retrofit{
            if(retro == null)
                synchronized(this){
                    retro = Retrofit.Builder().baseUrl(PATH).addConverterFactory(GsonConverterFactory.create()).build()
                }
            return retro!!
        }
    }
}