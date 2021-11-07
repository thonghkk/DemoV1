package com.example.coroutines_example.auto_complete_text

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.example.coroutines_example.R

class Adapter(context: Context, resourceId: Int, textView: Int, var model: List<Model>) :
    ArrayAdapter<Model>(context, resourceId, textView, model) {

    private var inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var view: View

    val mListSuggest = mutableListOf<Model>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        if (convertView == null) {
            view = inflater.inflate(R.layout.item_auto_complete, parent, false)
        }
        val name = view.findViewById<TextView>(R.id.textViewItemComplete)

        val congregation: Model? = getItem(position)
        if (congregation != null) {
            name.text = congregation.name
        } else {
            name.text = "no thing"
            return view
        }
        return view
    }

    override fun getCount(): Int {
        return model.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                Log.d("TAG", "performFiltering Above: $p0 ")
                val filterResult = FilterResults()

                if (!p0.isNullOrEmpty()) {
                    Log.d("TAG", "performFiltering Center: ${model.size}")
                    val filter = p0.toString().toLowerCase().trim()
                    for (m in model) {
                        if (m.name.toLowerCase().trim().contains(filter)) {
                            mListSuggest.add(m)
                        }
                    }
                }else{
                    mListSuggest.addAll(model)
                }

                filterResult.values = mListSuggest
                filterResult.count = mListSuggest.size
                Log.d("TAG", "performFiltering: ${mListSuggest.size}")

                return filterResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
               clear()
                if (p1?.values != null) {
                    addAll(p1.values as MutableList<Model>)
                }
                notifyDataSetInvalidated()
            }

            override fun convertResultToString(resultValue: Any?): CharSequence {
                val result = resultValue as Model
                Log.d("TAG", "getView___: ${result.name}")
                return result.name
            }

        }
    }

}