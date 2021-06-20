package com.example.coroutines_example.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.coroutines_example.R


class ThirdFragment : Fragment() {
    private lateinit var mBtnBack2:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_third, container, false)
        mBtnBack2 = v.findViewById(R.id.mBtnBackFm2)

        mBtnBack2.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return v
    }

}