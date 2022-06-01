package com.example.retrofitwithrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitwithrecyclerview.databinding.ItemRowBinding

class MyAdapter(
    private val userList: ArrayList<MyDataItem>
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(val viewDataBinding: ItemRowBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.b.text = userList[position].title
//        holder.userId.text = userList[position].userId.toString()

        //let,apply, also, run
        with(userList[position]) {
            holder.viewDataBinding.idText.text = this.userId.toString()
            holder.viewDataBinding.titleText.text = this.title
        }



    }

    override fun getItemCount(): Int {
        return userList.size
    }
}