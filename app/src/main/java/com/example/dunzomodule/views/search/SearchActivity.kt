package com.example.dunzomodule.views.search

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.dunzomodule.R
import com.example.dunzomodule.databinding.ActivitySearchBinding
import com.example.dunzomodule.views.search.viewmodel.SearchActivityViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var searchActivityViewModel: SearchActivityViewModel

    lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        searchActivityViewModel = ViewModelProviders.of(this, factory).get(SearchActivityViewModel::class.java)

        searchActivityViewModel.testFun()

        initObserver()
    }

    private fun initObserver() {
        searchActivityViewModel.observeForLiveData().observe(this, Observer { boolean ->
            Toast.makeText(this, "Live Data Observed", Toast.LENGTH_LONG).show()
        })
    }
}