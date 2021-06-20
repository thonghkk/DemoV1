package com.example.coroutines_example.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines_example.R
import com.example.coroutines_example.model.Model
import com.example.coroutines_example.save_status_of_fragment.MainSaveActivity
import com.example.coroutines_example.save_status_of_fragment.adapter.StateAdapter

class FirstFragment : Fragment() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mMainSaveActivity: MainSaveActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_first, container, false)
        mRecyclerView = v.findViewById(R.id.mRecycleViewSaveStatus)
        mMainSaveActivity = activity as MainSaveActivity

        mRecyclerView.layoutManager = LinearLayoutManager(context)

        //send data to Adapter and handle event Click listener
        val stateAdapter = StateAdapter(initData(),object :StateAdapter.IClickListener{
            override fun onClickItem(model: Model) {
                mMainSaveActivity.goToSecondFragment(model)
            }
        })
        mRecyclerView.adapter = stateAdapter

        return v
    }

    private fun initData():List<Model>{
        val listData = mutableListOf<Model>()
        for (i in 0 .. 20){
            listData.add(Model("Title $i","Description"))
        }
        return listData
    }
}