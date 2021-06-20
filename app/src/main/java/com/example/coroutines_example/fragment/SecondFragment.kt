package com.example.coroutines_example.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.coroutines_example.R
import com.example.coroutines_example.model.Model
import com.example.coroutines_example.save_status_of_fragment.MainSaveActivity

class SecondFragment : Fragment() {
    private lateinit var mTxtItem:TextView
    private lateinit var mBtnBackFirst:Button
    private lateinit var mBtnNextFm:Button
    private lateinit var mMainSaveActivity: MainSaveActivity

    companion object{
        val TAG = SecondFragment::class.simpleName
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_second, container, false)
        mTxtItem = v.findViewById(R.id.mTxtItem)
        mBtnBackFirst = v.findViewById(R.id.mBtnBackFm1)
        mBtnNextFm = v.findViewById(R.id.mBtnNextFm)
        mMainSaveActivity = activity as MainSaveActivity

        val bundle = arguments
        val model = bundle?.get("object") as Model
        mTxtItem.text = model.title

        mBtnNextFm.setOnClickListener {
            mMainSaveActivity.goToThirdFragment()
        }
        mBtnBackFirst.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return v
    }
}