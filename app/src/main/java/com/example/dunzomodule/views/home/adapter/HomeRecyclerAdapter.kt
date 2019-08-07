package com.example.dunzomodule.views.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzomodule.R
import com.example.dunzomodule.databinding.ItemRecyclerHomeBinding
import com.example.dunzomodule.views.home.model.items.ItemsInnerObjectDataModel

class HomeRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var itemDataModelList = ArrayList<ItemsInnerObjectDataModel>()

    fun addData(itemDataModelList: ArrayList<ItemsInnerObjectDataModel>) {
        this.itemDataModelList = itemDataModelList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemRecyclerHomeBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_recycler_home,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemDataModelList.get(position)
        (holder as ViewHolder).bind(item)
    }

    override fun getItemCount(): Int {
        if (itemDataModelList.size == 0)
            return 0
        else
            return itemDataModelList.size
    }

    class ViewHolder : RecyclerView.ViewHolder {
        var binding: ItemRecyclerHomeBinding

        constructor(itemView: ItemRecyclerHomeBinding) : super(itemView.getRoot()) {
            this.binding = itemView
        }

        fun bind(item: ItemsInnerObjectDataModel) {
            //binding.item = item
        }
    }
}