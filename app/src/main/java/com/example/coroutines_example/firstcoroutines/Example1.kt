package com.example.coroutines_example.firstcoroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Example1 {
    private fun hours(a: Int): Int {
        var mA = a
        if (mA >= 24) {
            mA = 0
        }
        return mA
    }

    private fun minute(b: Int): Int {
        var mB = b
        if (mB >= 60) {
            mB = 0
        }
        return mB
    }

    private fun seconds(c: Int): Int {
        var mC = c
        if (mC >= 60) {
            mC = 0
        }
        return mC
    }

    fun time(a: Int, b: Int, c: Int) {
        println("${hours(a)}:${minute(b)}:${seconds(c)}")
    }

    fun timeNew(a: Int, b: Int, c: Int) {
        var mA = a
        var mB = b
        var mC = c

        if (mA >= 24 || mA < 0 || mB >= 60 || mB < 0 || mC >= 60 || mC < 0) {
            println("Time undefine!!")
        } else {
            //mC++
            if (mC == 60) {
                mC = 0
                mA++
                if (mA == 24) {
                    mA = 0
                    mB = 0
                    mC = 0
                }
            }
            println("$mA : $mB : $mC")
        }
    }

    fun handleTime(a: Int, b: Int, c: Int) {
        var mA = a
        var mB = b
        var mC = c

        if (mC >= 60) {
            mC %= 60
            mB++
        }
        if (mB >= 60) {
            mB %= 60
            mA++
        }
        if (mA >= 24) {
            mA %= 24
        }
        println("$mA : $mB : $mC")
    }
}

//fun main(){
//    GlobalScope.launch {
//        delay(1000)
//        println("Hello")
//    }
//    println("World")
//    Thread.sleep(2000)
//}

//fun main(){
//    runBlocking {
//        delay(1000)
//        println("Hello")
//        delay(2000)
//        println("Coroutine")
//    }
//}

//fun main(){
//    val start = System.currentTimeMillis()
//    runBlocking {
//        repeat(1_000_000){
//             launch {
//                 println("hello coroutine")
//             }
//        }
//    }
//    val end = System.currentTimeMillis()
//
//    print("Time = ${end - start}ms")
//}

fun main() {
    val string1 = "06:59:15"
    val string2 = "12:59:30"

    val timeSeconds1 = string1.substring(6, 8)
    val timeMinute1 = string1.substring(3, 5)
    val timeHours1 = string1.substring(0, 2)
    println("$timeHours1 : $timeMinute1 : $timeSeconds1")

    val timeSeconds2 = string2.substring(6, 8)
    val timeMinute2 = string2.substring(3, 5)
    val timeHours2 = string2.substring(0, 2)
    println("$timeHours2 : $timeMinute2 : $timeSeconds2")

    val timeSeconds = timeSeconds1.toInt() + timeSeconds2.toInt()
    val timeMinute = timeMinute1.toInt() + timeMinute2.toInt()
    val timeHours = timeHours1.toInt() + timeHours2.toInt()


    // println("seconds : ${timeSeconds1.toInt() + timeSeconds2.toInt()}")


    val example = Example1()

    example.handleTime(timeHours, timeMinute, timeSeconds)

//    print("Enter hour1:")
//    val hour1: String = readLine().toString()
//    print("Enter minute1:")
//    val minute1: String = readLine().toString()
//    print("Enter seconds2:")
//    val second1: String = readLine().toString()
//    print("Enter hour2:")
//    val hour2: String = readLine().toString()
//    print("Enter minute2:")
//    val minute2: String = readLine().toString()
//    print("Enter seconds:")
//    val second2: String = readLine().toString()

    //example.timeNew(hour1.toInt(),minute1.toInt(),second1.toInt())

}