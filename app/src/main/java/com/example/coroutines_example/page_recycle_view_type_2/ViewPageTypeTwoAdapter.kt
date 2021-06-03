package com.example.coroutines_example.page_recycle_view_type_2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines_example.R
import com.example.coroutines_example.model.Model

const val TYPE_ITEM = 1
const val TYPE_LOADING = 2

class ViewPageTypeTwoAdapter(private val models: MutableList<Model>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //check Are Items was added ?
    var isLoadingAdd = false

    override fun getItemViewType(position: Int): Int {
        if (position == models.size - 1 && isLoadingAdd) {
            return TYPE_LOADING
        }
        return TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (TYPE_ITEM == viewType) {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
            return ViewPageHolder(v)
        } else {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_page_loading, parent, false)
            return ViewProgressHolder(v)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == TYPE_ITEM) {
            val model = models[position]
            val viewPageHolder = holder as ViewPageHolder
            viewPageHolder.bindItem(model)
        }
    }

    override fun getItemCount() = models.size


    class ViewPageHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mTxtTitle: TextView = view.findViewById(R.id.mTxtTitle)
        fun bindItem(models: Model) {
            "${models.title} ${position + 1}".also { mTxtTitle.text = it }
        }
    }

    class ViewProgressHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mProgressBar = view.findViewById<ProgressBar>(R.id.mProgressBarType2)
    }

    fun addFooterLoading() {
        isLoadingAdd = true
        models.add(Model("", ""))
    }

    fun removeFooterLoading() {
        isLoadingAdd = false
        val position = models.size - 1
        models.removeAt(position)
        notifyDataSetChanged()
    }
}