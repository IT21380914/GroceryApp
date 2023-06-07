package com.example.groceryapp.database.daos

import androidx.room.*
import com.example.groceryapp.database.entities.GroceryDaily


@Dao
interface GroceryDao {

    @Insert
    suspend fun insertGrocery(groceryDaily: GroceryDaily)

    @Delete
    suspend fun deleteGrocery(groceryDaily: GroceryDaily)

    @Update
     fun updateGrocery(groceryDaily: GroceryDaily)

    @Query("SELECT * FROM GroceryDaily")
    fun getAllDailyGrocery():List<GroceryDaily>

    @Query("SELECT COUNT(Amount) FROM GroceryDaily")
    fun getGroceyCount():Int
}