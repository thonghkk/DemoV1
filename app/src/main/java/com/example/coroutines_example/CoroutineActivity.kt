package com.example.coroutines_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutines_example.coutinescontext.TestDispatcher
import com.example.coroutines_example.coutinescontext.TestJob
import com.example.coroutines_example.coutinescontext.TestWithContext

class CoroutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

//        TestDispatcher.runMyFirstCoroutine()
        TestJob.check()

    }
}