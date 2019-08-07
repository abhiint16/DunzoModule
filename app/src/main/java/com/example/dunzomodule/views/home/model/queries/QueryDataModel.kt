package com.example.dunzomodule.views.home.model.queries

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class QueryDataModel(

    @SerializedName("previousPage")
    var previousPage: List<QueryInnerObjectDataModel> = ArrayList(),
    @SerializedName("request")
    var request: List<QueryInnerObjectDataModel> = ArrayList(),
    @SerializedName("nextPage")
    var nextPage: List<QueryInnerObjectDataModel> = ArrayList()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(QueryInnerObjectDataModel) as ArrayList,
        parcel.createTypedArrayList(QueryInnerObjectDataModel) as ArrayList,
        parcel.createTypedArrayList(QueryInnerObjectDataModel) as ArrayList
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(previousPage)
        parcel.writeTypedList(request)
        parcel.writeTypedList(nextPage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QueryDataModel> {
        override fun createFromParcel(parcel: Parcel): QueryDataModel {
            return QueryDataModel(parcel)
        }

        override fun newArray(size: Int): Array<QueryDataModel?> {
            return arrayOfNulls(size)
        }
    }

}