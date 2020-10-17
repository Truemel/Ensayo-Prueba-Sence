package com.example.ensayopruebasence.model.room

import androidx.lifecycle.LiveData
import com.example.ensayopruebasence.model.retrofit.EnsayoRetroDetail
import com.example.ensayopruebasence.model.retrofit.EnsayoRetroProduct

class EnsayoRoomDBManager(private val dao:EnsayoRoomDAO) {

    //request para insertar lista productos a base de datos
    suspend fun insertProductList(list: MutableList<EnsayoRetroProduct>){
        var rList:MutableList<EnsayoRoomProduct> = mutableListOf()
        for(prod in list)
            rList.add(EnsayoRoomProduct(prod.id, prod.name, prod.price, prod.image))
        dao.insertProductList(rList)
    }

    //request para insertar detalle de producto a base de datos
    suspend fun insertDetail(det: EnsayoRetroDetail){
        dao.insertDetail(EnsayoRoomDetail(det.id, det.name, det.price, det.image, det.description, det.lastPrice, det.credit))
    }

    //request para actualizar producto en base de datos
    suspend fun updateProduct(prod: EnsayoRetroProduct){
        dao.updateProduct(EnsayoRoomProduct(prod.id, prod.name, prod.price, prod.image))
    }

    //request para actualizar detalle en base de datos
    suspend fun updateDetail(det: EnsayoRetroDetail){
        dao.updateDetail(EnsayoRoomDetail(det.id, det.name, det.price, det.image, det.description, det.lastPrice, det.credit))
    }

    //request para obtener lista productos de room
    fun getProductsList(): LiveData<MutableList<EnsayoRoomProduct>>{
        return dao.getProductsList()
    }

    //request para obtener detalle producto de room
    fun getDetail(id:Int): LiveData<EnsayoRoomDetail>{
        return dao.getDetail(id)
    }

}