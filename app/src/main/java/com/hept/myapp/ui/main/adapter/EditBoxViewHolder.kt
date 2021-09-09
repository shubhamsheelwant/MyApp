package com.hept.myapp.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_edit_box.view.*

class EditBoxViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title = itemView.heading
    val editBox = itemView.editBox

}