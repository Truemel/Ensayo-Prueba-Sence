package com.example.ensayopruebasence.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ensayopruebasence.model.retrofit.EnsayoRetroDetail
import com.example.ensayopruebasence.model.retrofit.EnsayoRetroProduct
import com.example.ensayopruebasence.model.retrofit.EnsayoRetrofitRequests
import com.example.ensayopruebasence.model.room.EnsayoRoomDBManager
import com.example.ensayopruebasence.model.room.EnsayoRoomDataBase
import com.example.ensayopruebasence.model.room.EnsayoRoomDetail
import com.example.ensayopruebasence.model.room.EnsayoRoomProduct
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnsayoViewModel(application: Application):AndroidViewModel(application) {

    private val dbManager:EnsayoRoomDBManager
    val productList:LiveData<MutableList<EnsayoRoomProduct>>
    lateinit var detail:LiveData<EnsayoRoomDetail>

    init{
        dbManager = EnsayoRoomDBManager(EnsayoRoomDataBase.getDB(application).getDao())
        productList = dbManager.getProductsList()
        if(productList.value.isNullOrEmpty())
            getProdListRetro()
    }

    //obtiene detalles de room, de lo contrario lo obtiene de retrofit
    fun getDetails(id:Int){
        detail = dbManager.getDetail(id)

        if(detail.value == null || detail.value!!.name.isEmpty())
            getDetailsRetro(id)
    }

    fun insertProductsList(list: MutableList<EnsayoRetroProduct>) = viewModelScope.launch { dbManager.insertProductList(list) }

    fun insertDetail(det:EnsayoRetroDetail) = viewModelScope.launch { dbManager.insertDetail(det) }

    fun updateProduct(prod: EnsayoRetroProduct) = viewModelScope.launch { dbManager.updateProduct(prod) }

    fun updateDetail(det: EnsayoRetroDetail) = viewModelScope.launch { dbManager.updateDetail(det) }

    fun getProdListRetro(){
        EnsayoRetrofitRequests().getRetroProducts(object : Callback<MutableList<EnsayoRetroProduct>>{
            override fun onResponse(
                call: Call<MutableList<EnsayoRetroProduct>>,
                response: Response<MutableList<EnsayoRetroProduct>>
            ) {
                if(!response.body().isNullOrEmpty())
                    insertProductsList(response.body()!!)
            }

            override fun onFailure(call: Call<MutableList<EnsayoRetroProduct>>, t: Throwable) {
                Toast.makeText(getApplication(), "Error, couldn't get products list", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getDetailsRetro(id:Int){
        EnsayoRetrofitRequests().getRetroProductDetail(object :Callback<EnsayoRetroDetail>{
            override fun onResponse(
                call: Call<EnsayoRetroDetail>,
                response: Response<EnsayoRetroDetail>
            ) {
                if(response.body() != null)
                    insertDetail(response.body()!!)
            }

            override fun onFailure(call: Call<EnsayoRetroDetail>, t: Throwable) {
                Toast.makeText(getApplication(), "Error, couldn't get details", Toast.LENGTH_LONG).show()
            }
        }, id)
    }

}