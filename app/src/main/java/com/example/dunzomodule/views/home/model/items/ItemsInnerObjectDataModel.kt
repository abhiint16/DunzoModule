package com.example.dunzomodule.views.home.model.items

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ItemsInnerObjectDataModel(
    @SerializedName("title")
    var title: String?,
    @SerializedName("cacheId")
    var cacheId: String?,
    @SerializedName("snippet")
    var snippet: String?,
    @SerializedName("pagemap")
    var pagemap: PagemapDataModel?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(PagemapDataModel::class.java.classLoader) as PagemapDataModel?
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(cacheId)
        parcel.writeString(snippet)
        parcel.writeValue(pagemap)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemsInnerObjectDataModel> {
        override fun createFromParcel(parcel: Parcel): ItemsInnerObjectDataModel {
            return ItemsInnerObjectDataModel(parcel)
        }

        override fun newArray(size: Int): Array<ItemsInnerObjectDataModel?> {
            return arrayOfNulls(size)
        }
    }
}