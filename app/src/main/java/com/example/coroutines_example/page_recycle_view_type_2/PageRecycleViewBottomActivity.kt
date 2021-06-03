package com.example.coroutines_example.page_recycle_view_type_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines_example.R
import com.example.coroutines_example.model.Model
import com.example.coroutines_example.page_recycle_view_type_1.PaginationScroll

/**
 *Using recycle mutil
 * */
class PageRecycleViewBottomActivity : AppCompatActivity() {
    private lateinit var mRecycleViewPageType2: RecyclerView
    private lateinit var mViewPageTypeTwoAdapter: ViewPageTypeTwoAdapter
    var listModels = mutableListOf<Model>()
    var isLoading = false
    var isLastPage = false
    var currentPage = 1
    val totalPage = 5


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_recycle_view_bottom)

        mRecycleViewPageType2 = findViewById(R.id.mRecycleViewPageType2)
        val linearLayout = LinearLayoutManager(this)
        mRecycleViewPageType2.layoutManager = linearLayout
        setFirstData()
        mRecycleViewPageType2.adapter = mViewPageTypeTwoAdapter

        mRecycleViewPageType2.addOnScrollListener(object : PaginationScroll(linearLayout) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage += 1
                loadNextPage()
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

        })
    }

    private fun loadNextPage() {
        val handles = Handler()
        handles.postDelayed({
            val list = getListModel()

            mViewPageTypeTwoAdapter.removeFooterLoading()
            listModels.addAll(list)
            mViewPageTypeTwoAdapter.notifyDataSetChanged()
            isLoading = false

            if (currentPage < totalPage) {
                mViewPageTypeTwoAdapter.addFooterLoading()
            } else {
                isLastPage = true
            }
        }, 1000)
    }

    /*
    * Load data page 1
    * */
    fun setFirstData() {
        listModels = getListModel()
        mViewPageTypeTwoAdapter = ViewPageTypeTwoAdapter(listModels)
        if (currentPage < totalPage) {
            mViewPageTypeTwoAdapter.addFooterLoading()
        } else {
            isLastPage = true
        }
    }

    fun getListModel(): MutableList<Model> {
        val list = mutableListOf<Model>()
        for (i in 0 until 10) {
            list.add(Model(""))
        }

        return list
    }
}