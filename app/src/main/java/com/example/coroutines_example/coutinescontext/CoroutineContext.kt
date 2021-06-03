package com.example.coroutines_example.coutinescontext

import android.util.Log
import com.example.coroutines_example.CoroutineActivity
import kotlinx.coroutines.*

class CoroutineContext {
}

object TestDispatcher {
    @DelicateCoroutinesApi
    fun runMyFirstCoroutine(){
        GlobalScope.launch(Dispatchers.Default) {
            Log.d(CoroutineActivity::class.java.simpleName, "Dispatcher Default run on ${Thread.currentThread().name} ")
        }
        GlobalScope.launch(Dispatchers.IO) {
            Log.d(CoroutineActivity::class.java.simpleName, "Dispatcher IO run on ${Thread.currentThread().name} ")
        }
        GlobalScope.launch(Dispatchers.Main) {
            Log.d(CoroutineActivity::class.java.simpleName, "Before Delay - Dispatcher Main run on ${Thread.currentThread().name} ")
            delay(1000)
            Log.d(CoroutineActivity::class.java.simpleName, "After Delay - Dispatcher Main run on ${Thread.currentThread().name} ")
        }
        GlobalScope.launch(Dispatchers.Unconfined) {
            Log.d(CoroutineActivity::class.java.simpleName, "Before delay - Dispatcher Unconfined run on ${Thread.currentThread().name} ")

            delay(1000)
            Log.d(CoroutineActivity::class.java.simpleName, "After Delay - Dispatcher Unconfined run on ${Thread.currentThread().name} ")
        }
        GlobalScope.launch(newSingleThreadContext("My Thread")) {
            Log.d(CoroutineActivity::class.java.simpleName, "run on ${Thread.currentThread().name} ")
        }

     }
}


fun main(){


}