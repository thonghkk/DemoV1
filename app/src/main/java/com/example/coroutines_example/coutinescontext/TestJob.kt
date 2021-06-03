package com.example.coroutines_example.coutinescontext

import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*

object TestJob {
    fun testJoin() {
        val job: Job = GlobalScope.launch {
            delay(2000)
            println("Hello World")
        }

        val job2: Job = GlobalScope.launch {
            //join() : wait to when job finish running
            job.join()
             delay(1000)
            println("This my Coroutines")
        }
    }

    fun testCancel() {
        runBlocking {
            val job = launch {
                repeat(1000){
                    println("I'm Sleeping $it...")
                    delay(500)
                }
            }
            delay(3000)
            println("Main : I'm tired of waiting!")
            job.cancel()
            println("Main: Now i can quit")
        }
    }

    fun testCancelSecond() = runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextTime = startTime
            var i = 0
            while (isActive){
                if (System.currentTimeMillis() >= nextTime){
                    println("Job: I'm sleeping ${i++}..")
                    nextTime += 500
                }
            }
        }
        delay(1000)
        println("Main : I'm tired of waiting!")
        job.cancel()
        println("Main: Now i can quit")
    }

    fun finallyDemo() = runBlocking {
        val job = launch {
            try {
                repeat(1000){
                    println("I'm Sleeping $it...")
                    delay(500)
                }
            }finally {
                //final run before coroutine die
                    //delay() can check coroutine was dead or not dead
                println("I'm Running Finally")
            }
        }
        delay(1000)
        println("Main : I'm tired of waiting!")
        job.cancel()
        println("Main: Now i can quit")
    }

    fun immortalDemo() = runBlocking {
        val job = launch {
            try {
                repeat(1000){
                    println("I'm sleeping $it...")
                    delay(500)
                }
            }finally {
                withContext(NonCancellable){ //makes coroutine immortal
                    println("I'm Running Finally")
                    delay(1000)
                    println("I'm non-cancellable")
                }
            }
        }
        delay(1000)
        println("Main : I'm tired of waiting!")
        job.cancel()
        println("Main: Now i can quit")
    }

    fun timeoutDemo() = runBlocking {
        val result  = withTimeoutOrNull(1000){
            repeat(1000){
                println("I'm sleeping $it...")
                delay(500)
            }
        }
        println("Result is $result")
    }


     fun check(){
        val job = GlobalScope.launch {
            delay(1000)
            Log.d("check", "check: 1")
        }
         Log.d("check", "check: 2")
    }

}


 fun main() {
//     val func = TestJob()
//     func.check()

}