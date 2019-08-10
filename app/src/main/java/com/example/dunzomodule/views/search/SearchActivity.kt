package com.example.dunzomodule.views.search

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.dunzomodule.R
import com.example.dunzomodule.databinding.ActivitySearchBinding
import com.example.dunzomodule.views.baseview.BaseActivity
import com.example.dunzomodule.views.home.HomeActivity
import com.example.dunzomodule.views.search.viewmodel.SearchActivityViewModel
import javax.inject.Inject

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchActivityViewModel>(), View.OnClickListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override val layout: Int
        get() = R.layout.activity_search

    override fun setUp() {
        binding.proceedBtn.setOnClickListener(this)
        binding.searchStringEdittext.addTextChangedListener(usernameWatcher)
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(SearchActivityViewModel::class.java)
    }

    override fun initObserver() {
        viewModel.observeForLiveData().observe(this, Observer { boolean ->
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
            viewModel.saveSearchStringToPref(searchString)
        }
    }
}