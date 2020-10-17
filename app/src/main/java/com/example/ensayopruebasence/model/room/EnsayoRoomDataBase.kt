package com.example.ensayopruebasence.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EnsayoRoomProduct::class, EnsayoRoomDetail::class], version = 1, exportSchema = false)
abstract class EnsayoRoomDataBase:RoomDatabase() {

    companion object{
        private const val DB_NAME:String = "ensayo_db"
        private var ensayoDB:RoomDatabase? = null

        fun getDB(context:Context):RoomDatabase{
            if(ensayoDB == null)
                synchronized(this){
                    ensayoDB = Room.databaseBuilder(context, EnsayoRoomDataBase::class.java, DB_NAME).build()
                }
            return ensayoDB!!
        }
    }
}