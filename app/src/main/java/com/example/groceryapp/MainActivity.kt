package com.example.groceryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.database.GroceryDatabase
import com.example.groceryapp.database.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var btnAdd:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //attaching the adapter to recyler view

        val recylerView:RecyclerView=findViewById(R.id.rvGroceryList)
        val adapter=GroceriesAdapter()
        recylerView.adapter=adapter
        recylerView.layoutManager=LinearLayoutManager(this)

        //Retirieving data from database and displaying
        val ui=this
        val repository=Repository(GroceryDatabase.getInstance(this))

        CoroutineScope(Dispatchers.IO).launch {
            val data=repository.getAllDailyGrocery()
            //setting fecthed data to the adapater

            adapter.setData(data,ui)
        }

        //Add item button Functionality
        btnAdd=findViewById(R.id.btnAddItem)

        btnAdd.setOnClickListener{
            val intent= Intent(this,AddGrocceries::class.java)
            startActivity(intent)
            finish()
        }

    }
}