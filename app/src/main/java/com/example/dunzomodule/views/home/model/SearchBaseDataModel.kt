package com.example.dunzomodule.views.home.model

import android.os.Parcel
import android.os.Parcelable
import com.example.dunzomodule.views.home.model.items.ItemsInnerObjectDataModel
import com.example.dunzomodule.views.home.model.queries.QueryDataModel
import com.google.gson.annotations.SerializedName

class SearchBaseDataModel(

    @SerializedName("queries")
    var queries: QueryDataModel? = null,
    @SerializedName("items")
    var items: List<ItemsInnerObjectDataModel>? = null
) : Parcelable {

    constructor(parcel: Parcel) : this() {
        parcel.readValue(QueryDataModel::class.java.classLoader)
        parcel.readArrayList(ItemsInnerObjectDataModel::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(queries)
        parcel.writeTypedList(items)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchBaseDataModel> {
        override fun createFromParcel(parcel: Parcel): SearchBaseDataModel {
            return SearchBaseDataModel(parcel)
        }

        override fun newArray(size: Int): Array<SearchBaseDataModel?> {
            return arrayOfNulls(size)
        }
    }

}