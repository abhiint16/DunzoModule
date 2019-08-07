package com.example.dunzomodule.views.home.model.items

import android.os.Parcel
import android.os.Parcelable
import com.example.dunzomodule.views.home.model.queries.QueryInnerObjectDataModel
import com.google.gson.annotations.SerializedName

class PagemapDataModel(

    @SerializedName("cse_thumbnail")
    var cse_thumbnail: List<CseThumbnailDataModel>?,
    @SerializedName("cse_image")
    var cse_image: List<CseImageDataModel>?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(CseThumbnailDataModel),
        parcel.createTypedArrayList(CseImageDataModel)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(cse_thumbnail)
        parcel.writeTypedList(cse_image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PagemapDataModel> {
        override fun createFromParcel(parcel: Parcel): PagemapDataModel {
            return PagemapDataModel(parcel)
        }

        override fun newArray(size: Int): Array<PagemapDataModel?> {
            return arrayOfNulls(size)
        }
    }

}