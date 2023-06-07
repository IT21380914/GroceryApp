package com.example.groceryapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.groceryapp.database.GroceryDatabase
import com.example.groceryapp.database.Repository
import com.example.groceryapp.database.entities.GroceryDaily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddGrocceries : AppCompatActivity() {

    private lateinit var closebtn:Button
    private lateinit var edtItem:EditText
    private lateinit var edtAmount:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_grocceries)

        edtItem=findViewById(R.id.edtItem)
        edtAmount=findViewById(R.id.edtAmount)

        val btnInsertItem=findViewById<Button>(R.id.btnInsertItem)
        val repository=Repository(GroceryDatabase.getInstance(this))


        btnInsertItem.setOnClickListener{
            val item=edtItem.text.toString()
            val amount=edtAmount.text.toString()
            val grocery=GroceryDaily(item,amount)
            if(item.isEmpty()){
                Toast.makeText(this,"Please enter Item",Toast.LENGTH_LONG).show()
            }
            else{
                CoroutineScope(Dispatchers.IO).launch {
                    repository.insertGrocery(grocery)
                }
                Toast.makeText(this, "Item added", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        closebtn=findViewById(R.id.closebtn)
        closebtn.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}