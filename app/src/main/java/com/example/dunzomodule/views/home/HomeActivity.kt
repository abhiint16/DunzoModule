package com.example.dunzomodule.views.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzomodule.R
import com.example.dunzomodule.databinding.ActivityHomeBinding
import com.example.dunzomodule.views.home.adapter.HomeRecyclerAdapter
import com.example.dunzomodule.views.home.model.items.ItemsInnerObjectDataModel
import com.example.dunzomodule.views.home.viewmodel.HomeActivityViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var homeActivityViewModel: HomeActivityViewModel

    lateinit var binding: ActivityHomeBinding

    lateinit var mainRecyclerAdapter: HomeRecyclerAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        initBinding()
        initViewModel()
        initRecyclerView()



        homeActivityViewModel.getSearchData()

        initObserver()
    }

    private fun initViewModel() {
        homeActivityViewModel = ViewModelProviders.of(this, factory).get(HomeActivityViewModel::class.java)
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    private fun initDagger() {
        AndroidInjection.inject(this)
    }

    private fun initRecyclerView() {
        mainRecyclerAdapter = HomeRecyclerAdapter()
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.setLayoutManager(linearLayoutManager)
        binding.recyclerView.setAdapter(mainRecyclerAdapter)
    }

    private fun initObserver() {
        homeActivityViewModel.observeForBaseLiveData().observe(this, Observer { baseData ->
            mainRecyclerAdapter.addData(baseData.items as ArrayList<ItemsInnerObjectDataModel>)
        })
    }
}