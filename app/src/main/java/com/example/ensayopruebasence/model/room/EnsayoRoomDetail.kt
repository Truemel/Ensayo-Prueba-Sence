package com.example.ensayopruebasence.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details_table")
data class EnsayoRoomDetail(@PrimaryKey val id:Int, var name:String, var price:Int, var image:String, var description:String, var lastPrice:Int, var credit:Boolean)