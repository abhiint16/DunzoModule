package com.example.dunzomodule.views.home.model.queries

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class QueryInnerObjectDataModel(
    @SerializedName("title")
    var title: String?,
    @SerializedName("totalResults")
    var totalResults: String?,
    @SerializedName("searchTerms")
    var searchTerms: String?,
    @SerializedName("count")
    var count: String?,
    @SerializedName("startIndex")
    var startIndex: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(totalResults)
        parcel.writeString(searchTerms)
        parcel.writeString(count)
        parcel.writeString(startIndex)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QueryInnerObjectDataModel> {
        override fun createFromParcel(parcel: Parcel): QueryInnerObjectDataModel {
            return QueryInnerObjectDataModel(parcel)
        }

        override fun newArray(size: Int): Array<QueryInnerObjectDataModel?> {
            return arrayOfNulls(size)
        }
    }
}