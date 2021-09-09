package com.hept.myapp.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp.DropDownOptions
import com.example.myapp.FormFields
import com.hept.myapp.R
import com.hept.myapp.ui.main.view.MainActivity.Companion.DATE
import com.hept.myapp.ui.main.view.MainActivity.Companion.DROP_DOWN
import com.hept.myapp.ui.main.view.MainActivity.Companion.EDIT_BOX
import com.hept.myapp.ui.main.view.MainActivity.Companion.LOGO
import com.hept.myapp.ui.main.view.MainActivity.Companion.TEXT_AREA
import com.hept.myapp.utils.DatePickerUtil
import kotlin.collections.ArrayList

class MainAdapter(private var context: Context, private val formData: List<FormFields>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // This variable will hold the drop down data
    var dropDownList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return if (viewType == TYPE_LOGO) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_logo, parent, false)
            LogoViewHolder(view)
        } else if (viewType == TYPE_DROP_DOWN) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_dropdown, parent, false)
            DropDownViewHolder(view)
        } else if (viewType == TYPE_EDIT_BOX) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_edit_box, parent, false)
            EditBoxViewHolder(view)
        } else if (viewType == TYPE_DATE) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_date, parent, false)
            DateViewHolder(view)
        } else if (viewType == TYPE_TEXT_AREA) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_text_area, parent, false)
            TextAreaViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_logo, parent, false)
            LogoViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val data = formData[position]
        val context = holder.itemView.context

        when (holder.itemViewType) {
            TYPE_LOGO -> {
                val viewHolder = holder as LogoViewHolder
                viewHolder.title.text = data.label
                Glide.with(context).load(data.textAns).into(viewHolder.imagelogo)
            }
            TYPE_DROP_DOWN -> {
                val viewHolder = holder as DropDownViewHolder
                viewHolder.title.text = data.label
                prepareSpinnerData(data.dropDownOptions)
                setDropDownAdapter(viewHolder.dropDown, dropDownList)
            }
            TYPE_EDIT_BOX -> {
                val viewHolder = holder as EditBoxViewHolder
                viewHolder.title.text = data.label
            }
            TYPE_TEXT_AREA -> {
                val viewHolder = holder as TextAreaViewHolder
                viewHolder.title.text = data.label
            }
            TYPE_DATE -> {
                val viewHolder = holder as DateViewHolder
                viewHolder.title.text = data.label
                viewHolder.date.setOnClickListener {
                    DatePickerUtil.showDatePicker(context,viewHolder.date)
                }
            }
        }
    }

    /**
     * This method will prepare data to be shown on dropdown
     */
    private fun prepareSpinnerData(dropDownOptions: List<DropDownOptions>) {
        dropDownOptions.forEachIndexed { index, dropDownOptions ->
            dropDownList.add(dropDownOptions.viewText)
        }
    }

    /**
     * This method will set adapter for dropdown
     */
    private fun setDropDownAdapter(spinnerView: Spinner, dropDownOptions: ArrayList<String>) {
        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item, dropDownOptions
        )
        spinnerView.adapter = adapter
    }

    override fun getItemCount(): Int {
        return formData.size
    }

    override fun getItemViewType(position: Int): Int {
        val type = when (formData[position].fieldType) {
            LOGO -> TYPE_LOGO
            DROP_DOWN -> TYPE_DROP_DOWN
            EDIT_BOX -> TYPE_EDIT_BOX
            DATE -> TYPE_DATE
            TEXT_AREA -> TYPE_TEXT_AREA
            else -> 0
        }
        return type
    }

    companion object {
        private val TYPE_LOGO = 1
        private val TYPE_DROP_DOWN = 2
        private val TYPE_EDIT_BOX = 3
        private val TYPE_DATE = 4
        private val TYPE_TEXT_AREA = 5
    }
}
