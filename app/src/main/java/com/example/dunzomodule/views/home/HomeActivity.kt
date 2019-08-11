package com.example.dunzomodule.views.home

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzomodule.AppConstants
import com.example.dunzomodule.R
import com.example.dunzomodule.databinding.ActivityHomeBinding
import com.example.dunzomodule.views.baseview.BaseActivity
import com.example.dunzomodule.views.detail.DetailActivity
import com.example.dunzomodule.views.home.adapter.HomeRecyclerAdapter
import com.example.dunzomodule.views.home.viewmodel.HomeActivityViewModel
import com.example.dunzomodule.views.viewutil.CustomLinearLayoutManager
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var mainRecyclerAdapter: HomeRecyclerAdapter
    lateinit var linearLayoutManager: CustomLinearLayoutManager

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
        linearLayoutManager = CustomLinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.setLayoutManager(linearLayoutManager)
        binding.recyclerView.setAdapter(mainRecyclerAdapter)
        mainRecyclerAdapter.setViewModel(viewModel)
    }

    private fun getFirstPageData() {
        viewModel.getSearchData()
    }

}