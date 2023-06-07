package com.example.groceryapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.groceryapp.database.GroceryDatabase
import com.example.groceryapp.database.Repository
import com.example.groceryapp.database.entities.GroceryDaily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Integer.parseInt

class updateGroceries : AppCompatActivity() {
    private lateinit var btnClose:Button
    private lateinit var UpdateAmount:EditText
    private lateinit var UpdateItem:EditText
    private lateinit var btnUpdate:Button
    lateinit var groceryDaily:List<GroceryDaily>
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_groceries)


        UpdateAmount=findViewById(R.id.UpdateAmount)
        UpdateItem=findViewById(R.id.UpdateItem)
        btnUpdate=findViewById(R.id.Updatebtn)
        val repository=Repository(GroceryDatabase.getInstance(this))
        val adapter=GroceriesAdapter()
        UpdateItem.setText(intent.getStringExtra("Item"))
        UpdateAmount.setText(intent.getStringExtra("Amount"))

        val name=UpdateItem.text.toString()
        val amount=UpdateAmount.text.toString()
        var id=intent.getIntExtra("Key",0)
        var position=parseInt(id.toString())
        var grocery=GroceryDaily(name,amount)

        btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                     repository.updateGrocery(grocery)
            }
        }


        btnClose=findViewById(R.id.btnClose)
        btnClose.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}