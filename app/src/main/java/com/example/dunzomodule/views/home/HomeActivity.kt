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
import com.example.dunzomodule.views.detail.DetailActivity
import com.example.dunzomodule.views.home.adapter.HomeRecyclerAdapter
import com.example.dunzomodule.views.home.model.items.ItemsInnerObjectDataModel
import com.example.dunzomodule.views.home.viewmodel.HomeActivityViewModel
import dagger.android.AndroidInjection
import io.reactivex.functions.Consumer
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
        getFirstPageData()
        initObserver()
    }

    override fun onResume() {
        super.onResume()
        NetworkEvent.register(this, Consumer {
            when (it) {
                NetworkState.NO_INTERNET -> showToast("No Internet")

                NetworkState.NO_RESPONSE -> showToast("No Response")

                NetworkState.UNAUTHORISED -> showToast("Un Authorised")

                NetworkState.SUCCESS -> showToast("Success")

                NetworkState.BILLINGERROR -> showToast("Billing not done")

                NetworkState.RESPONSE -> showToast("Response")

                NetworkState.CX_NOT_DEFINED -> showToast("Cx is not sent in query")
            }
        })
    }

    private fun getFirstPageData() {
        homeActivityViewModel.getSearchData()
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
        mainRecyclerAdapter.setViewModel(homeActivityViewModel)
    }

    private fun initObserver() {
        homeActivityViewModel.observeForBaseLiveData().observe(this, Observer { baseData ->
            mainRecyclerAdapter.addData(baseData)
        })
        homeActivityViewModel.observeForErrorLiveData().observe(this, Observer { error ->
            showToast(error)
        })
        homeActivityViewModel.observeForItemClickLiveData().observe(this, Observer { itemData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(AppConstants.IntentKey.ITEM_CLICK_DATA, itemData)
            startActivity(intent)
        })
    }

    private fun showToast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}