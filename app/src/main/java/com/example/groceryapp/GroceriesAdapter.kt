package com.example.groceryapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.groceryapp.database.GroceryDatabase
import com.example.groceryapp.database.Repository
import com.example.groceryapp.database.entities.GroceryDaily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.zip.CheckedInputStream

class GroceriesAdapter:RecyclerView.Adapter<GroceriesAdapter.ViewHolder>(){
    lateinit var data:List<GroceryDaily>
    lateinit var context:Context
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val cbGrocery:CheckBox
        val ivUpdate:ImageView
        val ivDelete:ImageView

        init {
            cbGrocery=view.findViewById(R.id.cbGrocery)
            ivUpdate=view.findViewById(R.id.ivUpdate)
            ivDelete=view.findViewById(R.id.ivDelete)
        }
    }

    //adding the function to add data
     fun setData(data:List<GroceryDaily>,context: Context){
         this.data=data
        this.context=context
        notifyDataSetChanged()
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.view_grocery,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val repository=Repository(GroceryDatabase.getInstance(context))

        //diplaying the grocery value
        holder.cbGrocery.text="${data[position].Name} "+"${data[position].Amount}"

        //deleteing grocery at a certain position
        holder.ivDelete.setOnClickListener{
            if(holder.cbGrocery.isChecked){
                CoroutineScope(Dispatchers.IO).launch {
                    repository.deleteGrocery(data[position])
                    val  data=repository.getAllDailyGrocery()
                    withContext(Dispatchers.Main){
                        setData(data, context)
                    }
                }
            }else{
                Toast.makeText(context,"You cannot delete an item that's not checked",Toast.LENGTH_LONG).show()
            }
        }
        val groceryItem: String =data[position].Name
        val groceryAmount: String =data[position].Amount
        val groceryId: Int ?=data[position].id

        holder.ivUpdate.setOnClickListener{
         val intent=Intent(context,updateGroceries::class.java)
            val extras=Bundle()
            extras.putString("Item",groceryItem)
            extras.putString("Amount",groceryAmount)
            if (groceryId != null) {
                extras.putInt("Key",groceryId)
            }
            intent.putExtras(extras)
            context.startActivity(intent)
        }
    }
}