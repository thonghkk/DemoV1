 package com.example.coroutines_example.auto_complete_text

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import com.example.coroutines_example.R

 class AutoCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete)

        val autoCompleteText = findViewById<AutoCompleteTextView>(R.id.autoComplete)

        val modelAdapter = Adapter(this,R.layout.item_auto_complete,R.id.autoComplete,getListModel())
       // val modelAdapter = ModelAdapter(this,R.layout.item_auto_complete,getListModel())
        autoCompleteText.setAdapter(modelAdapter)

    }

     private fun getListModel():List<Model>{
         val list = mutableListOf<Model>()
         list.add(Model("Name 1"))
         list.add(Model("Name 2"))
         list.add(Model("Name 3"))
         list.add(Model("Name 4"))
         list.add(Model("Name 5"))
         list.add(Model("Name 6"))
         list.add(Model("Name 7"))
         return list
     }
}