package com.example.coroutines_example.coutinescontext.async_await

import kotlinx.coroutines.*

class FirstTest {
    fun total() = runBlocking {
        val startTime = System.currentTimeMillis()
        println("${printOne() + printTwo()}")
        val endTime = System.currentTimeMillis()
        println("time total = ${endTime - startTime}")

    }

    fun total2() = runBlocking {
        val startTime = System.currentTimeMillis()
        val one = async { printTwo() }
        val two = async { printOne() }
        println("Get Total is ${one.await() + two.await()}")
        val endTime = System.currentTimeMillis()
        println("time total = ${endTime - startTime}")

    }

    fun lazy() = runBlocking {
        val startTime = System.currentTimeMillis()
        val one = async(start = CoroutineStart.LAZY){printOne()}
        val two = async(start = CoroutineStart.LAZY){printTwo()}
        one.start()
        two.start()
        println("Get Total is ${one.await() + two.await()}")
        val endTime = System.currentTimeMillis()
        println("time total = ${endTime - startTime}")

    }

    fun globalScope() = runBlocking {
        val job = launch {
            GlobalScope.launch {
                println("Job 1 : Global scope begin")
                delay(1000)
                println("Job 1 : I am not ready")
            }
            launch {
                delay(100)
                println("Job 2 : This is my launch normal")
                delay(1000)
                println("Job 2 : Launch normal not ready ")
            }
        }
        delay(500)
        job.cancel()
        delay(1000)
        println("Main: End all launch")
    }

    private suspend fun printOne(): Int {
        delay(1000)
        return 10
    }

    private suspend fun printTwo(): Int {
        delay(500)
        return 20
    }

}

fun main() {
    val test = FirstTest()
//    test.total()
//    test.total2()
//    test.lazy()
    test.globalScope()
}