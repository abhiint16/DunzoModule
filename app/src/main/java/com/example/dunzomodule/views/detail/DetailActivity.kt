package com.example.dunzomodule.views.detail

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.dunzomodule.AppConstants
import com.example.dunzomodule.R
import com.example.dunzomodule.databinding.ActivityDetailBinding
import com.example.dunzomodule.views.baseview.BaseActivity
import com.example.dunzomodule.views.detail.viewmodel.DetailActivityViewModel
import com.example.dunzomodule.views.home.model.items.ItemsInnerObjectDataModel
import javax.inject.Inject

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailActivityViewModel>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var itemsInnerObjectDataModel: ItemsInnerObjectDataModel

    override val layout: Int
        get() = R.layout.activity_detail

    override fun initObserver() {
        viewModel.observeForLiveData().observe(this, Observer { boolean ->
            Toast.makeText(this, "Live Data Observed", Toast.LENGTH_LONG).show()
        })
    }

    override fun setUp() {
        itemsInnerObjectDataModel = intent.getParcelableExtra(AppConstants.IntentKey.ITEM_CLICK_DATA)!!
        binding.item = itemsInnerObjectDataModel
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(DetailActivityViewModel::class.java)
    }
}