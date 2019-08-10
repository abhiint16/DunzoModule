package com.example.dunzomodule.views.baseview

import android.app.Dialog
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.dunzomodule.utils.LoadingDialog
import com.example.dunzomodule.utils.network.NetworkEvent
import com.example.dunzomodule.utils.network.NetworkState
import dagger.android.AndroidInjection
import io.reactivex.functions.Consumer

abstract class BaseActivity<Binding : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    lateinit var binding: Binding

    lateinit var viewModel: VM

    lateinit var dialog: LoadingDialog

    @get:LayoutRes
    abstract val layout: Int

    protected abstract fun initObserver()

    protected abstract fun setUp()

    protected abstract fun initViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependencyInjection()
        initBinding()
        initViewModel()
        initObserver()
        setUp()
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

    override fun onStop() {
        super.onStop()
        NetworkEvent.unregister(this)
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, layout)
    }

    private fun initDependencyInjection() {
        AndroidInjection.inject(this)
    }

    protected fun showToast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun showLoading() {
        dialog = LoadingDialog(this@BaseActivity)
        dialog.show()
    }

    protected fun hideLoading() {
        if (dialog.isShowing)
            dialog.dismiss()
    }
}