package com.example.ensayopruebasence.model.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EnsayoRoomDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductList(list: MutableList<EnsayoRoomProduct>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(det:EnsayoRoomDetail)
    @Update
    suspend fun updateProduct(prod:EnsayoRoomProduct)
    @Update
    suspend fun updateDetail(det:EnsayoRoomDetail)
    @Query("SELECT * FROM products_table")
    fun getProductsList():LiveData<MutableList<EnsayoRoomProduct>>
    @Query("SELECT * FROM details_table WHERE `id`=:id")
    fun getDetail(id:Int):LiveData<EnsayoRoomDetail>
}