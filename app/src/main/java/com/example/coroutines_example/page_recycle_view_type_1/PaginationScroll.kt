package com.example.coroutines_example.page_recycle_view_type_1

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScroll(private var mLinearLayoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = mLinearLayoutManager.childCount
        val totalItemsCount = mLinearLayoutManager.itemCount
        val firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition ()

        if (isLoading() || isLastPage()){
            return
        }
        Log.d("item", "visibleItemCount :$visibleItemCount firstVisibleItemPosition:$firstVisibleItemPosition")
        if (firstVisibleItemPosition >= 0 && (visibleItemCount + firstVisibleItemPosition) >= totalItemsCount){
            loadMoreItems()
        }
    }

    abstract fun loadMoreItems()
    //check is it have loading items
    abstract fun isLoading():Boolean
    //check is it have last items
    abstract fun isLastPage():Boolean
}