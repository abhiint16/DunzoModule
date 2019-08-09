package com.example.dunzomodule.views.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.dunzomodule.views.home.model.items.ItemsInnerObjectDataModel

class RecyclerDiffCallback(
    private val mOldList: List<ItemsInnerObjectDataModel>,
    private val mNewList: List<ItemsInnerObjectDataModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return mOldList.size
    }

    override fun getNewListSize(): Int {
        return mNewList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].cacheId == mNewList[newItemPosition].cacheId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = mOldList[oldItemPosition]
        val newItem = mNewList[newItemPosition]

        return oldItem.title == newItem.title
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}