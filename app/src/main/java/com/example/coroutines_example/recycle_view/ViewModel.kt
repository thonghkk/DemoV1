package com.example.coroutines_example.recycle_view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel:ViewModel() {
    private val list = mutableListOf<Model>()

    val name = MutableLiveData<MutableList<Model>>()

    private fun addModel(model: MutableList<Model>){
        name.value = model
    }

    fun add(name:String){
        list.add(Model(name))
        addModel(list)
        Log.d("name2", "add: ${list[0].name} , $name")
    }
}