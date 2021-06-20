package com.example.coroutines_example.save_status_of_fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutines_example.R
import com.example.coroutines_example.fragment.FirstFragment
import com.example.coroutines_example.fragment.SecondFragment
import com.example.coroutines_example.fragment.SecondFragment.Companion.TAG
import com.example.coroutines_example.fragment.ThirdFragment
import com.example.coroutines_example.model.Model

class MainSaveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_save)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.mContainer, FirstFragment())
        fragmentTransaction.commit()
    }

    fun goToSecondFragment(model: Model) {
        //init sp fragment
        val fm = supportFragmentManager.beginTransaction()
        val secondFragment = SecondFragment()
        //paste data in bundle
        val bundle = Bundle()
        bundle.putSerializable("object",model)
        //send data to secondFragment
        secondFragment.arguments = bundle
        //replace current fragment
        fm.replace(R.id.mContainer,secondFragment)
        fm.addToBackStack(secondFragment.tag)
        //finally is commit
        fm.commit()
    }


    fun goToThirdFragment() {
        //init sp fragment
        val fm = supportFragmentManager.beginTransaction()
        val thirdFragment = ThirdFragment()
        //paste data in bundle
        //replace current fragment
        fm.replace(R.id.mContainer,thirdFragment)
        //add thirdFragment to BackStack
        fm.addToBackStack(thirdFragment.tag)
        //finally is commit
        fm.commit()
    }
}