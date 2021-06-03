package com.example.coroutines_example.recycle_view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines_example.R

class MainAdapter(private var model: MutableList<Model>, private val context: Context) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>(),Filterable {

    val oldModel:MutableList<Model> = model

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mTxtName: TextView = itemView.findViewById(R.id.txtName)
        fun bindItems(models: Model) {
            mTxtName.text = models.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(model[position])
        holder.itemView.findViewById<ImageView>(R.id.imgDelete).setOnClickListener {
            delete(position)
        }
        holder.itemView.findViewById<ImageView>(R.id.imgEdit).setOnClickListener {
            //edit(position, model[position])
            edit2(position,model[position])
        }

    }

    override fun getItemCount() = model.size

    private fun delete(position: Int) {
        model.removeAt(position)
        notifyDataSetChanged()
    }

    private fun edit(position: Int, models: Model) {
        val inflate: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflate.inflate(R.layout.item_edit_name, null)

        val name: EditText = view.findViewById(R.id.mEdtName)
        name.setText(models.name.toString())

        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            .setView(view)
            .setPositiveButton("Save", DialogInterface.OnClickListener { dialog, which ->
                model[position].name = name.text.toString()
                notifyDataSetChanged()
                Toast.makeText(context, "Update Successful", Toast.LENGTH_SHORT).show()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> })
        val alert: AlertDialog = builder.create()
        alert.show()

    }

    private fun edit2(position: Int, models: Model){
        val mDialog = Dialog(context)
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialog.setContentView(R.layout.item_edit_name_second)

        val window = mDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        val windowAtt:WindowManager.LayoutParams = window?.attributes!!
        windowAtt.gravity = View.TEXT_ALIGNMENT_GRAVITY
        window.attributes = windowAtt
        mDialog.show()

        val name = mDialog.findViewById<EditText>(R.id.mEdtName)
        name.setText(models.name)

        mDialog.findViewById<Button>(R.id.btnSave).setOnClickListener {
            model[position].name = name.text.toString()
            notifyDataSetChanged()
            mDialog.dismiss()
            Toast.makeText(context, "Update Successful", Toast.LENGTH_SHORT).show()

        }
        mDialog.findViewById<Button>(R.id.btnCancel).setOnClickListener {
            mDialog.dismiss()
        }

    }

    //search name
    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                 val strSearch = constraint.toString()
                if (strSearch.isEmpty()){
                    model = oldModel
                }else{
                    val list = mutableListOf<Model>()
                    for (i in oldModel){
                        if (i.name.toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(i)
                        }
                    }
                    model = list
                }
                val filterResult = FilterResults()
                filterResult.values = model
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                model = results?.values as MutableList<Model>
                notifyDataSetChanged()
             }

        }
    }
}