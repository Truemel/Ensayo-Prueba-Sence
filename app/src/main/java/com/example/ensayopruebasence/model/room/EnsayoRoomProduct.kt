package com.example.ensayopruebasence.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_table")
data class EnsayoRoomProduct(@PrimaryKey val id:Int, var name:String, var price:Int, var image:String)