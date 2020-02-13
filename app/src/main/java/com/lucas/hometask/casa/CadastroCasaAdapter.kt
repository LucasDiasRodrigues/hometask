package com.lucas.hometask.casa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.lucas.hometask.R

import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView

class CadastroCasaAdapter(internal var callback: OnItemClickListener) : RecyclerView.Adapter<CadastroCasaAdapter.MyViewHolder>() {

    internal var list: ArrayList<Int>
    internal var selectedPosition: Int? = null

    init {
        //this.list = list;
        selectedPosition = -1
        this.list = ArrayList()
        this.list.add(1)
        this.list.add(2)
        this.list.add(3)
        this.list.add(4)
        this.list.add(5)
        this.list.add(6)
        this.list.add(7)
        this.list.add(8)
        this.list.add(9)
        this.list.add(10)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_list_casa, parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.selected.visibility = if (selectedPosition == position)
            View.VISIBLE
        else
            View.GONE


        when (list[position]) {
            1 -> holder.imageView.setImageResource(R.drawable.house_1)
            2 -> holder.imageView.setImageResource(R.drawable.house_2)
            3 -> holder.imageView.setImageResource(R.drawable.house_3)
            4 -> holder.imageView.setImageResource(R.drawable.house_4)
            5 -> holder.imageView.setImageResource(R.drawable.house_1)
            6 -> holder.imageView.setImageResource(R.drawable.house_2)
            7 -> holder.imageView.setImageResource(R.drawable.house_3)
            8 -> holder.imageView.setImageResource(R.drawable.house_4)
            9 -> holder.imageView.setImageResource(R.drawable.house_1)
            10 -> holder.imageView.setImageResource(R.drawable.house_2)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var imageView: ImageView
        internal var selected: View

        init {
            imageView = v.findViewById(R.id.image)
            selected = v.findViewById(R.id.selected)
            v.setOnClickListener {
                selected.visibility = View.VISIBLE
                changeSelectedItem(adapterPosition)
                callback.onListClick(list[adapterPosition])
            }
        }
    }

    fun changeSelectedItem(adapterposition: Int) {
        var unselectedPosition = -1
        if (selectedPosition != -1)
            unselectedPosition = selectedPosition!!
        selectedPosition = adapterposition

        if (unselectedPosition != -1)
            notifyItemChanged(unselectedPosition)
    }

    interface OnItemClickListener {
        fun onListClick(integer: Int?)
    }
}


