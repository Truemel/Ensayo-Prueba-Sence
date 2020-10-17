package com.example.ensayopruebasence.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EnsayoRoomProduct::class, EnsayoRoomDetail::class], version = 1, exportSchema = false)
abstract class EnsayoRoomDataBase:RoomDatabase() {

    abstract fun getDao():EnsayoRoomDAO

    companion object{
        private const val DB_NAME:String = "ensayo_db"
        private var ensayoDB:EnsayoRoomDataBase? = null

        fun getDB(context:Context):EnsayoRoomDataBase{
            if(ensayoDB == null)
                synchronized(this){
                    ensayoDB = Room.databaseBuilder(context, EnsayoRoomDataBase::class.java, DB_NAME).build()
                }
            return ensayoDB!!
        }
    }
}