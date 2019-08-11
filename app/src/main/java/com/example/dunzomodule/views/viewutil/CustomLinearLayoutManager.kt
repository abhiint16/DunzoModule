package com.example.dunzomodule.views.viewutil

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomLinearLayoutManager(context: Context?, orientation: Int, reverseLayout: Boolean) : LinearLayoutManager(
    context,
    orientation,
    reverseLayout
) {

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: IndexOutOfBoundsException) {
            Log.e("CustomLayoutManager", "get rid of IOOBE in RecyclerView")
        }
    }
}
