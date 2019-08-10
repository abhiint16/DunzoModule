package com.example.dunzomodule.utils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import com.example.dunzomodule.R

class LoadingDialog(context: Context) : Dialog(context) {

    init {
        initView()
    }

    private fun initView() {
        setContentView(R.layout.item_loading)
        setCancelable(false)
    }

}