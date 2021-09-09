package com.hept.myapp.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_logo.view.*

class LogoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title = itemView.heading
    val imagelogo = itemView.imageLogo

}