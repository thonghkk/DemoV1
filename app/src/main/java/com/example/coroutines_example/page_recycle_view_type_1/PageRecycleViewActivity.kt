package com.example.coroutines_example.page_recycle_view_type_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines_example.R
import com.example.coroutines_example.model.Model

class PageRecycleViewActivity : AppCompatActivity() {
    private lateinit var mRecycleViewPage: RecyclerView
    private lateinit var mViewPageAdapter: ViewPageAdapter
    private lateinit var mProgressBar: ProgressBar
    private var listPage = mutableListOf<Model>()

    var isLoading = false
    var isLastPage = false
    var currentPage = 1
    val totalPage = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_recycle_view)
        mRecycleViewPage = findViewById(R.id.mRecycleViewPage)
        mProgressBar = findViewById(R.id.mProgressBar)

        val mLinearLayout = LinearLayoutManager(this)
//        val mItemDecoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
//        mRecycleViewPage.addItemDecoration(mItemDecoration)
        mRecycleViewPage.layoutManager = mLinearLayout
        listPage = importData()
        mViewPageAdapter = ViewPageAdapter(listPage)

        mRecycleViewPage.addOnScrollListener(object : PaginationScroll(mLinearLayout) {
            override fun loadMoreItems() {
                isLoading = true
                mProgressBar.visibility = View.VISIBLE
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
        mRecycleViewPage.adapter = mViewPageAdapter
    }

    private fun importData(): MutableList<Model> {
        Toast.makeText(this, "This is Page $currentPage", Toast.LENGTH_SHORT).show()
        val list = mutableListOf<Model>()
        for (i in 0 until 10) {
            list.add(Model("Title", "Description"))
        }
        return list
    }

    private fun loadNextPage() {
        val handles = Handler()
        handles.postDelayed({
            val list = importData()
            listPage.addAll(list)
            mViewPageAdapter.notifyDataSetChanged()

            isLoading = false
            mProgressBar.visibility = View.GONE
            if (currentPage == totalPage) {
                isLastPage = true
                Log.d("TAG", "loadNextPage: $currentPage $totalPage")
            }
        }, 1000)
    }
}