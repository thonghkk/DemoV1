package com.example.coroutines_example.save_status_of_fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines_example.R
import com.example.coroutines_example.model.Model

class StateAdapter(private val models: List<Model>, private val listener: IClickListener) :
    RecyclerView.Adapter<StateAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var mTitle = itemView.findViewById<TextView>(R.id.mTxtTitle)
        private var mDescription = itemView.findViewById<TextView>(R.id.mTxtDescription)
        val mLi: LinearLayout = itemView.findViewById(R.id.mLi)
        fun bindTo(model: Model) {
            mTitle.text = model.title
            mDescription.text = model.description

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = models[position]
        holder.bindTo(models[position])
        holder.mLi.setOnClickListener {
            listener.onClickItem(model)
        }
    }

    override fun getItemCount() = models.size

    interface IClickListener {
        fun onClickItem(model: Model)
    }
}