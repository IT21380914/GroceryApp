package com.example.groceryapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class GroceryDaily(var Name:String,var Amount:String,) {
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}