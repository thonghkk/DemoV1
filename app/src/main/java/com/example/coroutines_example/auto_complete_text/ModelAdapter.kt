package com.example.coroutines_example.auto_complete_text

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.example.coroutines_example.R
import java.util.*

class ModelAdapter(context: Context, resource: Int, objects: List<Model> ) :
    ArrayAdapter<Model>(context, resource, objects) {

    private val listModels = objects
    private val resourceLayout = resource
    private  var view: View? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
         view = convertView

        if (view == null) {
            val vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = vi.inflate(resourceLayout, parent, false)
            // convertView = LayoutInflater.from(parent.context).inflate(R.layout.item_auto_complete,parent,false)
        }

        val textView = convertView?.findViewById<TextView>(R.id.textViewItemComplete)
        val model = getItem(position)
        textView?.text = model?.name
        Log.d("TAG", "getView: ${model?.name}")
        return view!!
    }

        override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                Log.d("TAG", "performFiltering Above: $p0 ")
                val filterResult = FilterResults()

                val mListSuggest = mutableListOf<Model>()

//                if (p0.isNullOrEmpty()) {
//                    mListSuggest.addAll(listModels)
//                } else {
//                    val filter = p0.toString().toLowerCase().trim()
//                    for (model in listModels) {
//                        Log.d("TAG", "performFiltering Center: ${listModels.size}")
//                        if (model.name.toLowerCase().trim().contains(filter)) {
//                            mListSuggest.add(model)
//                        }
//                    }
//                }
                if (!p0.isNullOrEmpty()){
                    Log.d("TAG", "performFiltering Center: ${listModels.size}")
                    val filter = p0.toString().toLowerCase().trim()
                    for (model in listModels) {
                        if (model.name.toLowerCase().trim().contains(filter)) {
                            mListSuggest.add(model)
                        }
                    }
                    filterResult.values = mListSuggest
                    filterResult.count = mListSuggest.size
                }else{
                    mListSuggest.addAll(listModels)

                }

                Log.d("TAG", "performFiltering: ${mListSuggest.size}")

                return filterResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                clear()
                if (p1?.values != null){
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