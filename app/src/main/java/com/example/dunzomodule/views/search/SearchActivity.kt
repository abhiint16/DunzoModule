package com.example.dunzomodule.views.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.dunzomodule.R
import com.example.dunzomodule.databinding.ActivitySearchBinding
import com.example.dunzomodule.views.home.HomeActivity
import com.example.dunzomodule.views.search.viewmodel.SearchActivityViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var searchActivityViewModel: SearchActivityViewModel

    lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        initBinding()
        initViewModel()
        initObserver()
        setUp()
    }

    private fun initViewModel() {
        searchActivityViewModel = ViewModelProviders.of(this, factory).get(SearchActivityViewModel::class.java)
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
    }

    private fun initDagger() {
        AndroidInjection.inject(this)
    }

    private fun setUp() {
        binding.proceedBtn.setOnClickListener(this)
        binding.searchStringEdittext.addTextChangedListener(usernameWatcher)
    }

    private fun initObserver() {
        searchActivityViewModel.observeForLiveData().observe(this, Observer { boolean ->
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    var usernameWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            //do nothing
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //do nothing
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (binding.searchStringLayout.isErrorEnabled) {
                binding.searchStringLayout.setErrorEnabled(false)
            }
        }

    }

    override fun onClick(p0: View?) {
        val searchString = binding.searchStringEdittext.text.toString()

        if (searchString.isEmpty()) {
            binding.searchStringLayout.error = getString(R.string.search_string_error)
        } else {
            searchActivityViewModel.saveSearchStringToPref(searchString)
        }
    }
}