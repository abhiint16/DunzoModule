package com.example.dunzomodule.views.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzomodule.AppConstants
import com.example.dunzomodule.R
import com.example.dunzomodule.databinding.ActivityHomeBinding
import com.example.dunzomodule.utils.network.NetworkEvent
import com.example.dunzomodule.utils.network.NetworkState
import com.example.dunzomodule.views.baseview.BaseActivity
import com.example.dunzomodule.views.detail.DetailActivity
import com.example.dunzomodule.views.home.adapter.HomeRecyclerAdapter
import com.example.dunzomodule.views.home.model.items.ItemsInnerObjectDataModel
import com.example.dunzomodule.views.home.viewmodel.HomeActivityViewModel
import dagger.android.AndroidInjection
import io.reactivex.functions.Consumer
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var mainRecyclerAdapter: HomeRecyclerAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override val layout: Int
        get() = R.layout.activity_home

    override fun initObserver() {
        viewModel.observeForBaseLiveData().observe(this, Observer { baseData ->
            mainRecyclerAdapter.addData(baseData)
        })
        viewModel.observeForToolbarTitleLiveData().observe(this, Observer { toolbarTitle ->
            supportActionBar?.title = toolbarTitle
        })
        viewModel.observeForErrorLiveData().observe(this, Observer { error ->
            showToast(error)
        })
        viewModel.observeForItemClickLiveData().observe(this, Observer { itemData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(AppConstants.IntentKey.ITEM_CLICK_DATA, itemData)
            startActivity(intent)
        })
    }

    override fun setUp() {
        initRecyclerView()
        getFirstPageData()
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(HomeActivityViewModel::class.java)
    }

    private fun initRecyclerView() {
        mainRecyclerAdapter = HomeRecyclerAdapter()
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.setLayoutManager(linearLayoutManager)
        binding.recyclerView.setAdapter(mainRecyclerAdapter)
        mainRecyclerAdapter.setViewModel(viewModel)
    }

    private fun getFirstPageData() {
        viewModel.getSearchData()
    }

}