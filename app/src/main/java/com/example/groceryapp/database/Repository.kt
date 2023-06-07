package com.example.groceryapp.database

import com.example.groceryapp.database.entities.GroceryDaily

class Repository(private val db:GroceryDatabase) {
    suspend fun insertGrocery(groceryDaily: GroceryDaily)=db.getGroceryDao().insertGrocery(groceryDaily)
    suspend fun deleteGrocery(groceryDaily: GroceryDaily)=db.getGroceryDao().deleteGrocery(groceryDaily)
    suspend fun updateGrocery(groceryDaily: GroceryDaily)=db.getGroceryDao().updateGrocery(groceryDaily)
    fun getAllDailyGrocery()=db.getGroceryDao().getAllDailyGrocery()
}