package com.example.coroutines_example.page_recycle_view_type_1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines_example.R
import com.example.coroutines_example.model.Model

class ViewPageAdapter(private val models: List<Model>) :
    RecyclerView.Adapter<ViewPageAdapter.ViewPageHolder>() {
    class ViewPageHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mTxtTitle = view.findViewById<TextView>(R.id.mTxtTitle)
        private val mTxtDescription = view.findViewById<TextView>(R.id.mTxtDescription)
        @SuppressLint("SetTextI18n")
        fun bindItem(model: Model) {
            mTxtTitle.text = "${model.title} ${position + 1}"
            mTxtDescription.text = model.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPageHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        return ViewPageHolder(v)
    }

    override fun onBindViewHolder(holder: ViewPageHolder, position: Int) {
        holder.bindItem(models[position])
    }

    override fun getItemCount() = models.size
}