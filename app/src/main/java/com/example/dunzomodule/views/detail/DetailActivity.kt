package com.example.dunzomodule.views.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.dunzomodule.AppConstants
import com.example.dunzomodule.R
import com.example.dunzomodule.databinding.ActivityDetailBinding
import com.example.dunzomodule.views.detail.viewmodel.DetailActivityViewModel
import com.example.dunzomodule.views.home.model.items.ItemsInnerObjectDataModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var detailActivityViewModel: DetailActivityViewModel

    lateinit var binding: ActivityDetailBinding

    lateinit var itemsInnerObjectDataModel: ItemsInnerObjectDataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        initBinding()
        initViewModel()
        initObserver()
        setUp()
    }

    private fun setUp() {
        itemsInnerObjectDataModel = intent.getParcelableExtra(AppConstants.IntentKey.ITEM_CLICK_DATA)!!
        binding.item = itemsInnerObjectDataModel
    }

    private fun initViewModel() {
        detailActivityViewModel = ViewModelProviders.of(this, factory).get(DetailActivityViewModel::class.java)
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
    }

    private fun initDagger() {
        AndroidInjection.inject(this)
    }

    private fun initObserver() {
        detailActivityViewModel.observeForLiveData().observe(this, Observer { boolean ->
            Toast.makeText(this, "Live Data Observed", Toast.LENGTH_LONG).show()
        })
    }
}