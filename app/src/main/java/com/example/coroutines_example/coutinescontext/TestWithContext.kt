package com.example.coroutines_example.coutinescontext

import android.util.Log
import com.example.coroutines_example.CoroutineActivity
import kotlinx.coroutines.*

object TestWithContext {
    fun testMyFirstContext(){
        newSingleThreadContext("Thread1").use {context1->
            Log.d(CoroutineActivity::class.java.simpleName, "Context 1 - Thread : ${Thread.currentThread().name} ")
            newSingleThreadContext("Thread2").use {context2->
                Log.d(CoroutineActivity::class.java.simpleName, "Context 2 - Thread : ${Thread.currentThread().name} ")
                runBlocking(context1) {
                    Log.d(CoroutineActivity::class.java.simpleName, "Working in context 1- Thread : ${Thread.currentThread().name} ")
                    withContext(context2){
                        Log.d(CoroutineActivity::class.java.simpleName, "Working in context 2 - Thread : ${Thread.currentThread().name} ")
                    }
                    Log.d(CoroutineActivity::class.java.simpleName, "Back to  context 1 - Thread : ${Thread.currentThread().name} ")
                }
            }
        }
    }

    fun testMySecondContext(){
        GlobalScope.launch(Dispatchers.IO) {
            //run long time
            Log.d(CoroutineActivity::class.java.simpleName, "Run Long Time Task - Thread : ${Thread.currentThread().name} ")
            delay(2000)
            withContext(Dispatchers.Main){
                //Update Ui here
                Log.d(CoroutineActivity::class.java.simpleName, "Update UI - Thread : ${Thread.currentThread().name} ")
            }
        }
    }

}