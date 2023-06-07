package com.example.groceryapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.groceryapp.database.daos.GroceryDao
import com.example.groceryapp.database.entities.GroceryDaily
import com.example.groceryapp.database.entities.TobeBought

@Database(entities = [GroceryDaily::class], version = 1)
abstract class GroceryDatabase:RoomDatabase() {
    abstract fun getGroceryDao():GroceryDao
    companion object{
        @Volatile
        private var INSTANCE: GroceryDatabase? = null
        fun getInstance(context: Context):GroceryDatabase{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    GroceryDatabase::class.java,
                    "todo_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}