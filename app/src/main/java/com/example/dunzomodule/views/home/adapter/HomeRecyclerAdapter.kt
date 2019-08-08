package com.example.dunzomodule.views.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzomodule.AppConstants
import com.example.dunzomodule.R
import com.example.dunzomodule.databinding.ItemRecyclerHomeBinding
import com.example.dunzomodule.databinding.ItemRecyclerLoaderHomeBinding
import com.example.dunzomodule.views.home.model.SearchBaseDataModel
import com.example.dunzomodule.views.home.model.items.ItemsInnerObjectDataModel
import com.example.dunzomodule.views.home.model.queries.QueryDataModel
import com.example.dunzomodule.views.home.viewmodel.HomeActivityViewModel

class HomeRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var itemDataModelList: MutableList<ItemsInnerObjectDataModel> = ArrayList()

    private var queryDataModel: QueryDataModel? = QueryDataModel()

    private lateinit var homeActivityViewModel: HomeActivityViewModel

    fun addData(searchBaseDataModel: SearchBaseDataModel) {
        this.queryDataModel = searchBaseDataModel.queries
        searchBaseDataModel.items?.let { this.itemDataModelList.addAll(it) }
        notifyDataSetChanged()
    }

    fun setViewModel(homeActivityViewModel: HomeActivityViewModel) {
        this.homeActivityViewModel = homeActivityViewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == AppConstants.AdapterItemType.ITEM) {
            val binding: ItemRecyclerHomeBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recycler_home,
                parent,
                false
            )
            return ViewHolder(binding)
        } else {
            val binding: ItemRecyclerLoaderHomeBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recycler_loader_home,
                parent,
                false
            )
            return LoaderViewHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == AppConstants.AdapterItemType.ITEM) {
            val item = itemDataModelList.get(position)
            (holder as ViewHolder).bind(item, homeActivityViewModel)
        } else {
            homeActivityViewModel.getSearchData()
            (holder as LoaderViewHolder).bind()
        }
    }

    override fun getItemCount(): Int {
        if (queryDataModel?.nextPage != null && queryDataModel!!.nextPage.size != 0) {
            return itemDataModelList.size + 1
        } else {
            return itemDataModelList.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (itemDataModelList.size != 0 && position == itemDataModelList.size) {
            return AppConstants.AdapterItemType.LOADER
        } else {
            return AppConstants.AdapterItemType.ITEM
        }
    }

    class ViewHolder : RecyclerView.ViewHolder {
        var binding: ItemRecyclerHomeBinding

        constructor(itemView: ItemRecyclerHomeBinding) : super(itemView.getRoot()) {
            this.binding = itemView
        }

        fun bind(item: ItemsInnerObjectDataModel, viewModel: HomeActivityViewModel) {
            binding.item = item
            binding.viewModel = viewModel
        }
    }

    class LoaderViewHolder : RecyclerView.ViewHolder {
        var binding: ItemRecyclerLoaderHomeBinding

        constructor(itemView: ItemRecyclerLoaderHomeBinding) : super(itemView.getRoot()) {
            this.binding = itemView
        }

        fun bind() {
            //do nothing
        }
    }
}