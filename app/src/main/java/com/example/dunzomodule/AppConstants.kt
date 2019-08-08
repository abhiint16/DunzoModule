package com.example.dunzomodule

import androidx.annotation.IntDef

object AppConstants {

    object SharedPref {
        val SHARED_PREFERENCE_NAME = "DunzoPref"
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(AdapterItemType.LOADER, AdapterItemType.ITEM)
    annotation class AdapterItemType {
        companion object {
            const val LOADER = 100

            const val ITEM = 200
        }
    }

    object IntentKey {
        val ITEM_CLICK_DATA = "itemClickData"
    }
}
