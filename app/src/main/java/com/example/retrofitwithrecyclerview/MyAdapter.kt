package com.example.retrofitwithrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val context: Context, val userList: List<MyDataItem>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val userId : TextView
        val title: TextView

        init {
            userId = itemView.findViewById(R.id.idText)
            title = itemView.findViewById(R.id.titleText)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_row,parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = userList[position].title
        holder.userId.text = userList[position].userId.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}