package com.example.coroutines_example.recycle_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines_example.R

class MainActivity : AppCompatActivity() {
    private lateinit var mEdtName: EditText
    private lateinit var mBtnAddName: Button
    private lateinit var mSearchName: SearchView
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mViewModel: ViewModel
    private lateinit var mLinearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mEdtName = findViewById(R.id.edtName)
        mBtnAddName = findViewById(R.id.btnAddName)
        mSearchName = findViewById(R.id.mSearchName)
        mRecyclerView = findViewById(R.id.mRecycleView)
        mViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        mLinearLayoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = mLinearLayoutManager


        mBtnAddName.setOnClickListener {
            val mName = mEdtName.text.toString()
            if (mName.isEmpty()) {
                Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show()
            } else {
                mViewModel.add(mName)
                mEdtName.setText("")
                Toast.makeText(this, "Add Success", Toast.LENGTH_SHORT).show()
            }
        }

        mViewModel.name.observe(this, Observer { lists ->
            val mainAdapter = MainAdapter(lists, this)
            mRecyclerView.adapter = mainAdapter
            mLinearLayoutManager.scrollToPosition(lists.size - 1)
            searchName(mainAdapter)
            mainAdapter.notifyDataSetChanged()
        })
    }

    private fun searchName(mainAdapter: MainAdapter) {
        mSearchName.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mainAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mainAdapter.filter.filter(newText)
                return false
            }

        })
    }
}